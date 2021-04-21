package com.justterror.auto_kit.schedule_request.boundary;

import com.justterror.auto_kit.schedule_request.entity.ScheduleRequest;

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
@Path("/schedule_request")
public class ScheduleRequestResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ScheduleRequestService scheduleRequestService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScheduleRequest> listAll()
    {
        logger.info("Get all schedule requests");
        return scheduleRequestService.getAll();
    }

    @GET
    @Path("/schedule_request_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public ScheduleRequest getById(@QueryParam("id") long id) {
        logger.info("Get schedule request with id = " + id);
        return scheduleRequestService.getById(id);
    }

    @GET
    @Path("/schedule_request_vinId")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScheduleRequest> getByVinId(@QueryParam("vinId") long vinId) {
        logger.info("Get all schedule requests with VinId = " + vinId);
        return scheduleRequestService.getAllwithThisVinID(vinId);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewVinRange(@QueryParam("mileage") int mileage,
                                      @QueryParam("vin_id") long vinId, @QueryParam("ip") String ip) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new schedule request with parameters: mileage = %d, vin_id = %d, ip = %s",
                mileage, vinId, ip));
        try {
            scheduleRequestService.insertNewScheduleRequestTODB(mileage, vinId, ip);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on insterting new schedule request with parameters: mileage = %d, vin_id = %d, ip = %s",
                    mileage, vinId, ip));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteScheduleRequestWithID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting schedule request with id = %d", id));
        try {
            scheduleRequestService.deleteScheduleRequestByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting schedule request with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
