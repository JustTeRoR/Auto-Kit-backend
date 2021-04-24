package com.justterror.auto_kit.order_status.boundary;

import com.justterror.auto_kit.order_status.entity.OrderStatus;

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
@Path("/order_status")
public class OrderStatusResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private OrderStatusService orderStatusService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderStatus> listAll()
    {
        logger.info("Get all Order Statuses");
        return orderStatusService.getAll();
    }

    @GET
    @Path("/by_order_status_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public OrderStatus getById(@QueryParam("id") long id) {
        logger.info("Get Order Status with id = " + id);
        return orderStatusService.getById(id);
    }

    @GET
    @Path("/by_key")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public OrderStatus getById(@QueryParam("key") String key) {
        logger.info("Get Order Status with key = " + key);
        return orderStatusService.getByKey(key);
    }

}
