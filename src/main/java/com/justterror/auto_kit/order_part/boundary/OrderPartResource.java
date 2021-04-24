package com.justterror.auto_kit.order_part.boundary;

import com.justterror.auto_kit.order_part.entity.OrderPart;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.criteria.Order;
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
@Path("/order_part")
public class OrderPartResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private OrderPartService orderPartService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderPart> listAll()
    {
        logger.info("Get all order_parts ");
        return orderPartService.getAll();
    }

    @GET
    @Path("/by_order_part_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public OrderPart getById(@QueryParam("id") long id) {
        logger.info("Get order_parts with id = " + id);
        return orderPartService.getById(id);
    }

    @GET
    @Path("/by_order_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderPart> getByOrderId(@QueryParam("order_id") long orderId) {
        logger.info("Get order_parts with order_id = " + orderId);
        return orderPartService.getAllByOrderId(orderId);
    }

    @GET
    @Path("/by_order_part_status_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderPart> getByOrderPartStatusId(@QueryParam("order_part_status_id") long orderPartStatusId) {
        logger.info("Get all order_parts with order_part_status_id = " + orderPartStatusId);
        return orderPartService.getAllByOrderPartStatusId(orderPartStatusId);
    }

    @GET
    @Path("/by_part_provider_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderPart> getByPartProviderId(@QueryParam("part_provider_id") long partProviderId) {
        logger.info("Get all order_parts part_provider_id = " + partProviderId);
        return orderPartService.getAllByPartProviderId(partProviderId);
    }

    @GET
    @Path("/by_part_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderPart> getByPartId(@QueryParam("part_id") long partId) {
        logger.info("Get all order_parts with part_id = " + partId);
        return orderPartService.getAllByPartId(partId);
    }

    @POST
    @Path("/insert")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewPartModelYear(@QueryParam("order_id") long orderId, @QueryParam("order_part_status_id") long orderPartStatusId,
                                           @QueryParam("part_provider_id") long partProviderId, @QueryParam("purchase_price") BigDecimal purchasePrice,
                                           @QueryParam("price") BigDecimal price, @QueryParam("labour_price") BigDecimal labourPrice,
                                           @QueryParam("count") int count, @QueryParam("part_id") long partId) throws SQLException {
        logger.log(Level.INFO, String.format("Inserting new order_part with parameters: order_id = %d and part_id = %d", orderId, partId));
        try {
            orderPartService.insertNewOrderPartTODB(orderId,orderPartStatusId,partProviderId, purchasePrice, price, labourPrice, count, partId);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on inserting order_part with parameters: order_id = %d and part_id = %d", orderId, partId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderPartByID(@QueryParam("id") long id) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting order_part with id = %d", id));
        try {
            orderPartService.deleteOrderPartByID(id);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting order_part with id = %d", id));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_by_order_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderPartByOrderId(@QueryParam("order_id") long orderId) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting order_part with order_id = %d", orderId));
        try {
            orderPartService.deleteOrderPartByOrderId(orderId);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting order_part with order_id = %d", orderId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
