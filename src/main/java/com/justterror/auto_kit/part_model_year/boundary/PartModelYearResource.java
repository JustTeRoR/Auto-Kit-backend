package com.justterror.auto_kit.part_model_year.boundary;

import com.justterror.auto_kit.part_model_year.entity.PartModelYear;
import com.justterror.auto_kit.utils.ResponsesFactory;

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
@Path("/part_model_year")
public class PartModelYearResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private PartModelYearService partModelYearService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartModelYear> listAll()
    {
        logger.info("Get all part model years");
        return partModelYearService.getAll();
    }

    @GET
    @Path("/by_part_model_year_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public PartModelYear getById(@QueryParam("id") long id) {
        logger.info("Get part model year with id = " + id);
        return partModelYearService.getById(id);
    }

    @GET
    @Path("/by_model_year_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartModelYear> getByModelYearId(@QueryParam("model_year_id") long modelYearId) {
        logger.info("Get all part model years with model_year_id = " + modelYearId);
        return partModelYearService.getAllByModelYearId(modelYearId);
    }

    @GET
    @Path("/by_measure_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartModelYear> getByMeasureId(@QueryParam("measure_id") long measureId) {
        logger.info("Get all part model years with measure_id = " + measureId);
        return partModelYearService.getAllByMeasureId(measureId);
    }

    @GET
    @Path("/by_part_type_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartModelYear> getByPartTypeId(@QueryParam("part_type_id") long partTypeId) {
        logger.info("Get all part model years with part_type_id = " + partTypeId);
        return partModelYearService.getAllByPartTypeId(partTypeId);
    }

    @GET
    @Path("/by_oem_part_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartModelYear> getByOEMPartId(@QueryParam("oem_part_id") long oemPartId) {
        logger.info("Get all part model years with oem_part_id = " + oemPartId);
        return partModelYearService.getAllByOEMPartId(oemPartId);
    }

    @GET
    @Path("/by_part_type_and_model_year")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByModelYearId(@QueryParam("part_type_id") long partTypeId, @QueryParam("model_year_id") long modelYearId) {
        logger.info("Get part with part_type_id = " + partTypeId + " and model_year_id = " + modelYearId);
        List<Object[]> listResponse = partModelYearService.getAllByCategoryAndPartModelYear(partTypeId, modelYearId);
        String jsonResponse = ResponsesFactory.extendResponsePartModelYearByCategoryAndModelYearId(listResponse);
        return Response
                .status(Response.Status.OK)
                .entity(jsonResponse)
                .build();
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewPartModelYear(@QueryParam("model_year_id") long modelYearId, @QueryParam("part_type_id") long partTypeId,
                                      @QueryParam("measure_id") long measureId, @QueryParam("oem_part_id") long oemPartId,
                                           @QueryParam("labour") int labour, @QueryParam("quantity") int quantity, @QueryParam("part_name") String partName) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new part model year with parameters: model_year_id = %d and etc.", modelYearId));
        try {
            partModelYearService.insertNewPartModelYearTODB(modelYearId, partTypeId, measureId, oemPartId, labour, quantity, partName);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on part model year with parameters: model_year_id = %d and etc.", modelYearId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePartModelYearWithID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting part model year with id = %d", id));
        try {
            partModelYearService.deletePartModelYearByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting part model year with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
