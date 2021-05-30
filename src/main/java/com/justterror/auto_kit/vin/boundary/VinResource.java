package com.justterror.auto_kit.vin.boundary;

import com.justterror.auto_kit.vin.entity.Vin;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.transaction.*;
import javax.transaction.NotSupportedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@ApplicationScoped
@Path("/vin")
public class VinResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private VinService vinService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vin> listAll()
    {
        logger.info("Get all VINs from vin table");
        return vinService.getAll();
    }

    @GET
    @Path("/by_vin_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Vin getById(@QueryParam("id") long id) {
        logger.info("Get vin with id = " + id);
        return vinService.getById(id);
    }

    @GET
    @Path("/by_vin")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Vin getByVinField(@QueryParam("vin") String vin) {
        logger.info("Get all VINs from vin table with vin = " + vin);
        return vinService.getByVin(vin);
    }

    @POST
    @Path("/parseVin")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewVin(@QueryParam("vin") String vin, @QueryParam("user_ids") long userId ) throws SQLException, IOException {
        logger.log(Level.INFO, String.format("Parsing vin which equals = %s", vin));
        try {
            String response = vinService.parseRequestedVIN(vin, userId);
            return Response
                    .status(Response.Status.OK)
                    .entity(response)
                    .build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on Parsing vin which equals = %s", vin));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVinWithID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting vin with id = %d", id));
        try {
            vinService.deleteVinByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting vin with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_vin")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVinWithVin(@QueryParam("vin") String vin) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting vin with vin = %s", vin));
        try {
            vinService.deleteVinByVin(vin);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting vin with vin = %s", vin));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
