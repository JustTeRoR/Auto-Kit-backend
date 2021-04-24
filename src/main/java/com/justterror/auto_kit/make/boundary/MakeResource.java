package com.justterror.auto_kit.make.boundary;

import com.justterror.auto_kit.make.entity.Make;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;

//TODO:: to debug this

@ApplicationScoped
@Path("/make")
public class MakeResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private MakeService makeService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Make> listAll()
    {
        logger.info("Get all makes ");
        return makeService.getAll();
    }

    @GET
    @Path("/by_make_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Make getById(@QueryParam("id") long id) {
        logger.info("Get make with id = " + id);
        return makeService.getById(id);
    }

    @GET
    @Path("/by_name")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Make> getByName(@QueryParam("name") String name) {
        logger.info("Get make with name = " + name);
        return makeService.getAllByName(name);
    }

    @GET
    @Path("/by_vpic_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Make> getByVpicId(@QueryParam("vpic_id") long VpicId) {
        logger.info("Get make with vpic_id = " + VpicId);
        return makeService.getAllByVpicId(VpicId);
    }
}
