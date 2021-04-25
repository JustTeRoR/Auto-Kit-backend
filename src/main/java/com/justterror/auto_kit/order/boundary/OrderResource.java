package com.justterror.auto_kit.order.boundary;

import com.justterror.auto_kit.order.entity.Order;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
@Path("/order")
public class OrderResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private OrderService orderService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> listAll()
    {
        logger.info("Get all orders ");
        return orderService.getAll();
    }

    @GET
    @Path("/by_order_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Order getById(@QueryParam("id") long id) {
        logger.info("Get order with id = " + id);
        return orderService.getById(id);
    }

    @GET
    @Path("/by_user_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByUserId(@QueryParam("user_id") long userId) {
        logger.info("Get all orders with user_id = " + userId);
        return orderService.getAllByUserId(userId);
    }

    @GET
    @Path("/by_order_status_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByOrderPartStatusId(@QueryParam("order_status_id") long orderStatusId) {
        logger.info("Get all orders with order_status_id = " + orderStatusId);
        return orderService.getAllByStatusId(orderStatusId);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewOrder(@QueryParam("order_status_id") long orderStatusId, @QueryParam("price") BigDecimal price,
                                           @QueryParam("user_id") long userId) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new order with parameters: user_id = %d", userId));
        try {
            orderService.createOrderTODB(orderStatusId,price,userId);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on inserting order with parameters: user_id = %d", userId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderPartByID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting order with id = %d", id));
        try {
            orderService.deleteOrderByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting order with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
