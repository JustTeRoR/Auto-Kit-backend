package com.justterror.auto_kit.order_part.boundary;

import com.justterror.auto_kit.order.boundary.OrderService;
import com.justterror.auto_kit.order_part.entity.OrderPart;
import com.justterror.auto_kit.part.boundary.PartService;
import com.justterror.auto_kit.part.entity.Part;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Stateless
public class OrderPartService {

    @Inject
    Logger logger;

    @Inject
    OrderService orderService;

    @Inject
    PartService partService;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public OrderPart getById(long  id) {
        String rawQuery = String.format("FROM OrderPart WHERE id = %d", id);
        TypedQuery<OrderPart> query = entityManager.createQuery(rawQuery, OrderPart.class);
        return query.getSingleResult();
    }

    public List<OrderPart> getAll() {
        TypedQuery<OrderPart> query = entityManager.createQuery("select p from OrderPart p", OrderPart.class);
        return query.getResultList();
    }

    public List<OrderPart> getAllByOrderId(long orderId) {
        String rawQuery = String.format("FROM OrderPart WHERE order_id = %d", orderId);
        TypedQuery<OrderPart> query = entityManager.createQuery(rawQuery, OrderPart.class);
        return query.getResultList();
    }

    public List<OrderPart> getAllByPartProviderId(long partProviderId) {
        String rawQuery = String.format("FROM OrderPart WHERE part_provider_id = %d", partProviderId);
        TypedQuery<OrderPart> query = entityManager.createQuery(rawQuery, OrderPart.class);
        return query.getResultList();
    }

    public List<OrderPart> getAllByOrderPartStatusId(long orderPartStatusId) {
        String rawQuery = String.format("FROM OrderPart WHERE order_part_status_id = %d", orderPartStatusId);
        TypedQuery<OrderPart> query = entityManager.createQuery(rawQuery, OrderPart.class);
        return query.getResultList();
    }

    public List<OrderPart> getAllByPartId(long partId) {
        String rawQuery = String.format("FROM OrderPart WHERE part_id = %d", partId);
        TypedQuery<OrderPart> query = entityManager.createQuery(rawQuery, OrderPart.class);
        return query.getResultList();
    }

    //ORDER ID is SAME for all orderParts until they are moved with order next from initial state
    public List<Object[]> getExtendedAllOrderPartsByUserId(long userId) {
        String rawQuery = String.format("select op.id, op.order_id, op.order_part_status_id, ops.title as order_part_status_title, " +
                "op.part_provider_id, pp.name as part_provider_name, op.purchase_price,op.price, op.labour_price, op.count, op.part_id, " +
                "p.serial_number, o.user_id from order_part op inner join order_part_status ops on op.order_part_status_id = ops.id " +
                "inner join part_provider pp on op.part_provider_id = pp.id inner join part p on op.part_id=p.id " +
                "inner join \"order\" o on op.order_id = o.id where o.user_id =%d", userId);
        Query query = entityManager.createNativeQuery(rawQuery);
        return query.getResultList();
    }

    public void addPartToShoppingCart(long partId, long userId, long partProviderId) throws SQLException {
        //1. Check if user has order in status Open.
        String rawQuery = String.format("select o.id, o.order_status_id, os.key, os.title, o.price, o.creation_date, o.change_date, o.user_id from \"order\" o " +
                "inner join order_status os on o.order_status_id = os.id where o.user_id=%d and os.key IN ('Created')", userId);
        Query query = entityManager.createNativeQuery(rawQuery);
        List<Object[]> initialStateOrders = query.getResultList();
        Part toBeAddedPart = partService.getById(partId);

        if (initialStateOrders.size() == 0) {
            //order_status_id=1 Initial state "Created"
            orderService.createOrderTODB(1,toBeAddedPart.getLastPurchasePrice(), userId);
            initialStateOrders = query.getResultList();
        }
        //TODO:: To improve creation, let user select partProvider + change logic of price setting and mb count.
        OrderPart insertOrderPart = new OrderPart(Long.parseLong(initialStateOrders.get(0)[0].toString()), 1, partProviderId, toBeAddedPart.getLastPurchasePrice(), toBeAddedPart.getLastPurchasePrice().add(BigDecimal.valueOf(1500)), BigDecimal.valueOf(1500), 1, partId);
        entityManager.persist(insertOrderPart);
        orderService.updateOrderPrice(insertOrderPart.getOrderId(), insertOrderPart.getPrice());
    }

    public void updateCountOfOrderPartById(long id, int count)  throws SQLException{
        String rawQuery = String.format(Locale.US,"UPDATE order_part SET count = %d WHERE id = %d", count, id);
        Query query = entityManager.createNativeQuery(rawQuery);
        query.executeUpdate();
    }

    public void deleteOrderInInitialStateIfNoOrderParts(long orderId) throws SQLException {
        //order parts always have order_id which is in Initial State
        List<OrderPart> orderParts = getAllByOrderId(orderId);
        if (orderParts.size() == 0) {
            orderService.deleteOrderByID(orderId);
        }
    }

    public void deleteOrderPartByID(long id) throws SQLException{
        OrderPart orderPart = getById(id);
        String queryString = String.format("DELETE FROM \"order_part\" WHERE id IN (%d)", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
        deleteOrderInInitialStateIfNoOrderParts(orderPart.getOrderId());
    }

    public void deleteSeveralOrderPartById(String orderIdsString) throws SQLException{
        String[] ordersId = orderIdsString.split(",");
        long[] orderIds = new long[ordersId.length];
        for (int i = 0; i < ordersId.length; i++)
        {
            orderIds[i] = Long.parseLong(ordersId[i]);
        }
        OrderPart orderPart;
        if (orderIds.length != 0) {
            //all orderParts will have same order_id
            orderPart = getById(orderIds[0]);
            String queryString = String.format("DELETE FROM \"order_part\" WHERE id IN (%s)", orderIdsString);
            Query query = entityManager.createNativeQuery(queryString);
            query.executeUpdate();
            deleteOrderInInitialStateIfNoOrderParts(orderPart.getOrderId());
        }
    }
}
