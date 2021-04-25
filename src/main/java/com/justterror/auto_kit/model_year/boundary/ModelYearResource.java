package com.justterror.auto_kit.model_year.boundary;

import com.justterror.auto_kit.model_year.entity.ModelYear;

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
@Path("/model_year")
public class ModelYearResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ModelYearService modelYearService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModelYear> listAll()
    {
        logger.info("Get all ModelYears ");
        return modelYearService.getAll();
    }

    @GET
    @Path("/by_model_year_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public ModelYear getById(@QueryParam("id") long id) {
        logger.info("Get ModelYear with id = " + id);
        return modelYearService.getById(id);
    }

    @GET
    @Path("/by_user_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModelYear> getByUserId(@QueryParam("user_id") long userId) {
        logger.info("Get all ModelYear with user_id = " + userId);
        return modelYearService.getAllByUserId(userId);
    }

    @GET
    @Path("/by_model_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModelYear> getByModelId(@QueryParam("model_id") long modelId) {
        logger.info("Get all ModelYear with model_id = " + modelId);
        return modelYearService.getAllByModelId(modelId);
    }

    //TODO: To implement method of insertion

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderPartByID(@QueryParam("id") long id) throws SQLException {
        logger.log(Level.INFO, String.format("Deleting modelYear with id = %d", id));
        try {
            modelYearService.deleteModelYearByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting modelYear with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
