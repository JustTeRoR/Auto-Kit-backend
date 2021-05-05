package com.justterror.auto_kit.user.boundary;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@ApplicationScoped
@Path("/user")
public class UserResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private UserService userService;

    @GET
    @Path("/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        logger.log(Level.INFO, "login");
        if (securityContext.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                    .add("user", securityContext.getCallerPrincipal().getName())
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }

    @PUT
    @Path("/updatePhoneNumber")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewPartModelYear(@QueryParam("user_ids") long id, @QueryParam("phone") String phone) throws SQLException {
        logger.log(Level.INFO, String.format("Update phone number for user with parameters: id = %d", id));
        try {
            userService.updateUsersPhone(id,phone);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on updating phone number with parameters: id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
