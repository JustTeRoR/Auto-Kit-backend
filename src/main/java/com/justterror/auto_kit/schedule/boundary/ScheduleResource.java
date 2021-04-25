package com.justterror.auto_kit.schedule.boundary;

import com.justterror.auto_kit.schedule.entity.Schedule;
import com.justterror.auto_kit.vin.entity.Vin;

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
@Path("/schedule")
public class ScheduleResource {
    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ScheduleService scheduleService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Schedule> listAll()
    {
        logger.info("Get all Schedules from schedule table");
        return scheduleService.getAll();
    }

    @GET
    @Path("/by_schedule_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Schedule getById(@QueryParam("id") long id) {
        logger.info("Get Schedule with id = " + id);
        return scheduleService.getById(id);
    }

    @GET
    @Path("/by_part_model_year_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Schedule> getByPartModelYearId(@QueryParam("part_model_year_id") long partModelYearID) {
        logger.info("Get all Schedules with part_model_year_id = " + partModelYearID);
        return scheduleService.getByPartModelYearID(partModelYearID);
    }

    @GET
    @Path("/by_model_year_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Schedule> getByVinField(@QueryParam("model_year_id") long modelYearID) {
        logger.info("Get all Schedules with part_model_year_id = " + modelYearID);
        return scheduleService.getByModelYearID(modelYearID);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewVin(@QueryParam("mileage") long mileage, @QueryParam("part_model_year_id") long partModelYearID,
                                 @QueryParam("model_year_id") long modelYearID ) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new Schedule with parameters: mileage = %d, part_model_year_id = %d ,model_year_id = %d",
                mileage, partModelYearID, modelYearID));
        try {
            scheduleService.insertNewScheduleTODB(mileage,partModelYearID, modelYearID);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on insterting new Schedule with parameters: mileage = %d, part_model_year_id = %d ,model_year_id = %d",
                    mileage, partModelYearID, modelYearID));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteScheduleWithID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting schedule with id = %d", id));
        try {
            scheduleService.deleteScheduleByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting schedule with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_part_model_year")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteScheduleWithPartModelYear(@QueryParam("part_model_year_id") long partModelYearId) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting schedule with part_model_year = %d", partModelYearId));
        try {
            scheduleService.deleteScheduleByPartModelYearID(partModelYearId);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting schedule with part_model_year = %d", partModelYearId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_model_year")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteScheduleWithModelYear(@QueryParam("model_year_id") long modelYearId) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting schedule with model_year = %d", modelYearId));
        try {
            scheduleService.deleteScheduleByModelYearID(modelYearId);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting schedule with model_year = %d", modelYearId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
