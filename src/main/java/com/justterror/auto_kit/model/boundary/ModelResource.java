package com.justterror.auto_kit.model.boundary;

import com.justterror.auto_kit.model.entity.Model;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@ApplicationScoped
@Path("/model")
public class ModelResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ModelService modelService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Model> listAll()
    {
        logger.info("Get all models ");
        return modelService.getAll();
    }

    @GET
    @Path("/by_model_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Model getById(@QueryParam("id") long id) {
        logger.info("Get Model with id = " + id);
        return modelService.getById(id);
    }

    @GET
    @Path("/by_name")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Model> getByName(@QueryParam("name") String name) {
        logger.info("Get Model with name = " + name);
        return modelService.getAllByName(name);
    }

    @GET
    @Path("/by_make_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Model> getByMakeId(@QueryParam("make_id") long makeId) {
        logger.info("Get Model with make_id = " + makeId);
        return modelService.getAllByMakeID(makeId);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewOrder(@QueryParam("make_id") long makeId, @QueryParam("vpic_id") long vpicId,
                                   @QueryParam("name") String name, @QueryParam("trim_name") String trim_name ) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new model with parameters: make_id = %d, name = %s", makeId, name));
        try {
            modelService.insertModelTODB(makeId,vpicId,name,trim_name);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on inserting model with parameters: make_id = %d, name = %s", makeId, name));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderPartByID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting model with id = %d", id));
        try {
            modelService.deleteModelByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting model with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }


}
