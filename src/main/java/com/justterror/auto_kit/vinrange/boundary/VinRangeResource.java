package com.justterror.auto_kit.vinrange.boundary;

import com.justterror.auto_kit.vinrange.entity.VinRange;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

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
    @Path("/by_vin_range_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public VinRange getById(@QueryParam("id") String id) {
        logger.info("Get VIN range with id = " + id);
        return vinRangeService.getById(id);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewVinRange(@QueryParam("id") String id, @QueryParam("model_year_id") String modelYearId,
                                      @QueryParam("vin_mask") String vinMask) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new vin range with parameters: id = %s, model_yead_id = %s, vin_mask = %s",
                id, modelYearId, vinMask));
        try {
            vinRangeService.insertNewVinRangeTODB(id, modelYearId, vinMask);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on insterting new vin range with parameters: id = %s, model_yead_id = %s, vin_mask = %s",
                    id, modelYearId, vinMask));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
