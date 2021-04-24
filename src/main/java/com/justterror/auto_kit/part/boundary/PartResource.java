package com.justterror.auto_kit.part.boundary;

import com.justterror.auto_kit.part.entity.Part;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

//TODO:: to debug this

@ApplicationScoped
@Path("/part")
public class PartResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private PartService partService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Part> listAll()
    {
        logger.info("Get all parts");
        return partService.getAll();
    }

    @GET
    @Path("/by_part_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Part getById(@QueryParam("id") long id) {
        logger.info("Get part with id = " + id);
        return partService.getById(id);
    }

    @GET
    @Path("/serial_number")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Part> getByModelYearId(@QueryParam("serial_number") String serialNumber) {
        logger.info("Get part with serial_number = " + serialNumber);
        return partService.getAllBySerialNumber(serialNumber);
    }

    @GET
    @Path("/by_measure_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Part> getByMeasureId(@QueryParam("measure_id") long measureId) {
        logger.info("Get all parts with measure_id = " + measureId);
        return partService.getAllByMeasureId(measureId);
    }

    @GET
    @Path("/by_part_type_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Part> getByPartTypeId(@QueryParam("part_type_id") long partTypeId) {
        logger.info("Get all parts part_type_id = " + partTypeId);
        return partService.getAllByPartTypeId(partTypeId);
    }

    @GET
    @Path("/by_make_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Part> getByMakeId(@QueryParam("make_id") long makeId) {
        logger.info("Get all parts with make_id = " + makeId);
        return partService.getAllByMakeId(makeId);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewPartModelYear(@QueryParam("quantity") int quantity, @QueryParam("measure_id") long measureId,
                                           @QueryParam("make_id") long makeId, @QueryParam("part_type_id") long partTypeId,
                                           @QueryParam("is_oem") boolean isOEM, @QueryParam("last_purchase_price") BigDecimal lastPurchasePrice,
                                           @QueryParam("last_delivery_time") Date lastDeliveryTime, @QueryParam("serial_number") String serialNumber) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new part with parameters: serial_number = %s and etc.", serialNumber));
        try {
            partService.insertNewPartTODB(quantity,measureId,makeId, partTypeId, isOEM, lastPurchasePrice, lastDeliveryTime, serialNumber);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on inserting part with parameters: serial_number = %s and etc.", serialNumber));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePartByID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting part with id = %d", id));
        try {
            partService.deletePartByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting part with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_serial_number")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePartBySerialNumber(@QueryParam("serial_number") String serialNumber) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting part with serial_number = %s", serialNumber));
        try {
            partService.deletePartBySerialNumber(serialNumber);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting part with serial_number = %s", serialNumber));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}