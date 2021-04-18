package com.justterror.car_kit.user.boundary;

import com.justterror.car_kit.make.boundary.MakeService;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @POST
    @Path("/register/{username}&{password}")
    @PermitAll
    public Response registerUser(@PathParam("username") String username, @PathParam("password") String password)  {
        logger.info("Register new person");
        userService.register(username, password);
        return Response.ok().build();
    }


}
