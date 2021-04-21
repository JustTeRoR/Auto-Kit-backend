package com.justterror.auto_kit.vinrange.boundary;

import com.justterror.auto_kit.vinrange.entity.VinRange;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;

@ApplicationScoped
@Path("/vin_range")
public class VinRangeResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private VinRangeService vinRangeService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<VinRange> listAll()
    {
        logger.info("Get all VIN ranges");
        return vinRangeService.getAll();
    }

    @GET
    @Path("vin_range_id/{id}")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public VinRange getById(@PathParam("id") String id) {
        logger.info("Get VIN ranges with id = " + id);
        return vinRangeService.getById(id);
    }
}
