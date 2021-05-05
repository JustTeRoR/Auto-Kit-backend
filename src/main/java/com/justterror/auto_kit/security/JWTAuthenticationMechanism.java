package com.justterror.auto_kit.security;

import com.justterror.auto_kit.part.boundary.PartService;
import com.justterror.auto_kit.user.boundary.UserService;
import io.jsonwebtoken.ExpiredJwtException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.RememberMe;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.justterror.auto_kit.security.Constants.*;

@RememberMe(
        cookieMaxAgeSeconds = REMEMBERME_VALIDITY_SECONDS,
        isRememberMeExpression = "self.isRememberMe(httpMessageContext)"
)

@RequestScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {
    @Inject
    Logger logger;

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private UserService userService;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) {
/*
        logger.log(Level.INFO, "validateRequest: {0}", request.getRequestURI());
       //TODO:: In further updates remove password from url string
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String token = extractToken(context);

            if (name != null && password != null) {
            logger.log(Level.INFO, "credentials : {0}, {1}", new String[]{name, password});
            // validation of the credential using the identity store
            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(name, password));
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                // Communicate the details of the authenticated user to the container and return SUCCESS.
                return createToken(result, context);
            }
            // if the authentication failed, we return the unauthorized status in the http response
                return context.responseUnauthorized();
        } else if (token != null) {
            // validation of the jwt credential
            return validateToken(token, context);
        } else if (context.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return context.responseUnauthorized();
        }
        // there are no credentials AND the resource is not protected,
        // SO Instructs the container to "do nothing"
        return context.doNothing();
        */
        logger.log(Level.INFO, "validateRequest: {0}", request.getRequestURI());
        String user_ids = request.getParameter("user_ids");
        String access_token = request.getParameter("access_token");
        String version  = "5.92";
        String token = extractToken(context);
        if (user_ids != null && access_token != null) {
            logger.log(Level.INFO, "credentials : {0}, {1}", new String[]{user_ids, access_token});
            try {
                if (validateVkUser(version, access_token,user_ids)) {
                    String username = getExternalVKuserName(version, access_token, user_ids);
                    if (userService.isUserDuplicate(username)) {
                        CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, access_token));
                        return context.notifyContainerAboutLogin(result);
                    } else {
                        long id = Long.parseLong(user_ids);
                        userService.registerNewUser(id, access_token, username);
                        CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, access_token));
                        return context.notifyContainerAboutLogin(result);
                    }
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            // if the authentication failed, we return the unauthorized status in the http response
            return context.responseUnauthorized();
        }
        else if (token != null) {
            // validation of the jwt credential
            return validateToken(token, context);
        } else if (context.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return context.responseUnauthorized();
        }
        // there are no credentials AND the resource is not protected,
        // SO Instructs the container to "do nothing"
        return context.doNothing();
    }

    /**
     * To validate the JWT token e.g Signature check, JWT claims
     * check(expiration) etc
     *
     * @param token The JWT access tokens
     * @param context
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            logger.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result the result from validation of UsernamePasswordCredential
     * @param context
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
            context.getResponse().setHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        }
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
            return token;
        }
        return null;
    }

    /**
     * this function invoked using RememberMe.isRememberMeExpression EL
     * expression
     *
     * @param context
     * @return The remember me flag
     */
    public Boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }

    public Boolean validateVkUser(String version, String access_token, String user_ids) throws IOException {
        String vkUserName = getExternalVKuserName(version, access_token, user_ids);
        if (!vkUserName.equals(" ")) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getExternalVKuserName(String version, String access_token, String user_ids) throws IOException {
        String rawUrl = String.format("https://api.vk.com/method/users.get?v=%s&user_ids=%s&access_token=%s",version, user_ids, access_token);
        URL validateVkUser = new URL(rawUrl);
        HttpURLConnection connection = (HttpURLConnection) validateVkUser.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String responseStr = response.toString();
        String regexp1 = "\"(first_name)\":\"((\\ \"|[^\"])*)";
        String regexp2 = "\"(last_name)\":\"((\\ \"|[^\"])*)";
        Pattern pattern = Pattern.compile(regexp1);
        Pattern pattern1 = Pattern.compile(regexp2);
        Matcher matcher = pattern.matcher(responseStr);
        Matcher matcher1 = pattern1.matcher(responseStr);
        if (matcher.find() && matcher1.find())
        {
            String name = matcher.group(2);
            String surname = matcher1.group(2);
            return name + " " + surname;
        } else {
            return " ";
        }
    }
}
