package com.justterror.auto_kit.replaceability.boundary;

import com.justterror.auto_kit.replaceability.entity.Replaceablility;

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

//TODO:: TO debug this

@ApplicationScoped
@Path("/replaceability")
public class ReplaceablilityResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ReplaceablilityService replaceablilityService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Replaceablility> listAll()
    {
        logger.info("Get all replaceability mappings from vin table");
        return replaceablilityService.getAll();
    }

    @GET
    @Path("/by_replaceablility_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Replaceablility getById(@QueryParam("id") long id) {
        logger.info("Get replaceability mapping  with id = " + id);
        return replaceablilityService.getById(id);
    }

    @GET
    @Path("/by_part_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Replaceablility> getByPartIdField(@QueryParam("part_id") long partId) {
        logger.info("Get all replaceability mappings from replaceability table with part_id = " + partId);
        return replaceablilityService.getByPartID(partId);
    }

    @GET
    @Path("/by_part_model_year_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Replaceablility> getByPartModelYearIdField(@QueryParam("part_model_year_id") long partModelYearId) {
        logger.info("Get all replaceability mappings from replaceability table with part_model_year_id = " + partModelYearId);
        return replaceablilityService.getByPartModelYearID(partModelYearId);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewReplaceability(@QueryParam("part_id") long partId, @QueryParam("part_model_year_id") long partModelYearId ) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new replaceability with parameters: part_id = %d, part_model_year_id = %d",
                partId, partModelYearId));
        try {
            replaceablilityService.insertNewReplaceabilityTODB(partId, partModelYearId);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on insterting new replaceability with parameters: part_id = %d, part_model_year_id = %d",
                    partId, partModelYearId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReplaceabilityWithID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting replaceability with id = %d", id));
        try {
            replaceablilityService.deleteReplaceabilityByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting replaceability with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
