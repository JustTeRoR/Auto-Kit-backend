package com.justterror.auto_kit.vinrange.boundary;

import com.justterror.auto_kit.vinrange.entity.VinRange;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
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
    @Path("/vin_range_id/{id}")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public VinRange getById(@PathParam("id") String id) {
        logger.info("Get VIN ranges with id = " + id);
        return vinRangeService.getById(id);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewVinRange(@QueryParam("id") String id, @QueryParam("model_year_id") String modelYearId,
                                      @QueryParam("vin_mask") String vinMask) {
        logger.log(Level.INFO, String.format("Inserting new vin range with parameters: id = %s, model_yead_id = %s, vin_mask = %s",
                id, modelYearId, vinMask));

        vinRangeService.insertNewVinRangeTODB(id, modelYearId, vinMask);
        return Response.ok().build();
        //TODO:: Улучшить метод, самообновление айди + ветвление и отлов ошибки, не только успешный респонс
    }
}
