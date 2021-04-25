package com.justterror.auto_kit.order_part_status.boundary;

import com.justterror.auto_kit.order_part_status.entity.OrderPartStatus;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;

@ApplicationScoped
@Path("/order_part_status")
public class OrderPartStatusResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private OrderPartStatusService orderPartStatusService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderPartStatus> listAll()
    {
        logger.info("Get all Order Part Statuses");
        return orderPartStatusService.getAll();
    }

    @GET
    @Path("/by_order_part_status_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public OrderPartStatus getById(@QueryParam("id") long id) {
        logger.info("Get Order Part Status with id = " + id);
        return orderPartStatusService.getById(id);
    }

    @GET
    @Path("/by_key")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public OrderPartStatus getById(@QueryParam("key") String key) {
        logger.info("Get Order Part Status with key = " + key);
        return orderPartStatusService.getByKey(key);
    }
}
