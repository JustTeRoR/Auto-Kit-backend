package com.justterror.auto_kit.order_part.boundary;

import com.justterror.auto_kit.order_part.entity.OrderPart;
import com.justterror.auto_kit.utils.ResponsesFactory;

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
    @Path("/by_user_ids")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUserId(@QueryParam("user_ids") long userId) {
        logger.info("Get order_parts with user_id = " + userId);
        List<Object[]> listResponse= orderPartService.getExtendedAllOrderPartsByUserId(userId);
        String jsonResponse = ResponsesFactory.extendResponseOrderPartByOrderId(listResponse);
        return Response
                .status(Response.Status.OK)
                .entity(jsonResponse)
                .build();
    }

    @GET
    @Path("/by_order_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByOrderId(@QueryParam("order_id") long orderId) {
        logger.info("Get order_parts with order_id = " + orderId);
        List<Object[]> listResponse= orderPartService.getExtendedAllOrderPartsByOrderId(orderId);
        String jsonResponse = ResponsesFactory.extendResponseOrderPartByOrderId(listResponse);
        return Response
                .status(Response.Status.OK)
                .entity(jsonResponse)
                .build();
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
    @Path("/add_order_part")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderPart(@QueryParam("part_provider_id") long partProviderId, @QueryParam("part_id") long partId,
                                           @QueryParam("user_ids") long userId) throws SQLException {
        logger.log(Level.INFO, String.format("Adding new order_part with parameters: user_ids = %d and part_id = %d", userId, partId));
        try {
            orderPartService.addPartToShoppingCart(partId,userId,partProviderId);
            return Response.ok().build();
        } catch (SQLException exeption) {
            logger.log(Level.WARNING, String.format("ERROR on adding order_part with parameters: user_ids = %d and part_id = %d", userId, partId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/update_count")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountForOrderPart(@QueryParam("order_part_id") long orderPartId, @QueryParam("count") int count)  throws SQLException {
        logger.log(Level.INFO, String.format("Update count for order_part with parameters: order_part_id = %d and count = %d", orderPartId, count));
        try {
            orderPartService.updateCountOfOrderPartById(orderPartId, count);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on updating count for order_part with parameters: order_part_id = %d and count = %d", orderPartId, count));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/perform_order")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountForOrderPart(@QueryParam("order_id") long orderId)  throws SQLException {
        logger.log(Level.INFO, String.format("Perform order in Ordered state with id = %d", orderId));
        try {
            orderPartService.putOrderPartsAndOrderTOOrderedStatusByOrderId(orderId);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on performing order in Ordered state with id = %d", orderId));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete_single_by_id")
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
    @Path("/delete_multi_by_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSeveralOrderPartById(@QueryParam("string_ids") String  orderIdsString) throws SQLException{
        logger.log(Level.INFO, String.format("Deleting order_part with order_ids = %s", orderIdsString));
        try {
            orderPartService.deleteSeveralOrderPartById(orderIdsString);
            return Response.ok().build();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, String.format("ERROR on deleting order_part with order_ids = %s", orderIdsString));
            return Response.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
