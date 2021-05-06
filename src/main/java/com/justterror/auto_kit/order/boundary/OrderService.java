package com.justterror.auto_kit.order.boundary;


import com.justterror.auto_kit.order.entity.Order;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Stateless
public class OrderService {
    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Order getById(long  id) {
        String rawQuery = String.format("FROM Order WHERE id = %d", id);
        TypedQuery<Order> query = entityManager.createQuery(rawQuery, Order.class);
        return query.getSingleResult();
    }

    public List<Order> getAll() {
        TypedQuery<Order> query = entityManager.createQuery("select p from Order p", Order.class);
        return query.getResultList();
    }

    public List<Order> getAllByUserId(long userId) {
        String rawQuery = String.format("FROM Order WHERE user_id = %d", userId);
        TypedQuery<Order> query = entityManager.createQuery(rawQuery, Order.class);
        return query.getResultList();
    }

    public List<Object[]> getAllUserNotInitialStateOrders(long userId, String stateKey) {
        String rawQuery = String.format("select o.id, o.order_status_id, os.key, os.title, o.price, o.creation_date, o.change_date, o.user_id from \"order\" o " +
                "inner join order_status os on o.order_status_id = os.id where o.user_id=%d and os.key NOT IN ('%s')", userId, stateKey);
        Query query = entityManager.createNativeQuery(rawQuery);
        return query.getResultList();
    }

    public void updateOrderPrice(long id, BigDecimal price) {
        String rawQuery = String.format(Locale.US,"UPDATE \"order\" SET price = %f WHERE id =%d", price, id);
        Query query = entityManager.createNativeQuery(rawQuery);
        query.executeUpdate();
    }

    public List<Order> getAllByStatusId(long statusId) {
        String rawQuery = String.format("FROM Order WHERE order_status_id = %d", statusId);
        TypedQuery<Order> query = entityManager.createQuery(rawQuery, Order.class);
        return query.getResultList();
    }

    public void createOrderTODB(long orderStatusId, BigDecimal price, long userId) throws SQLException {
        Order insertOrder = new Order(orderStatusId, price, LocalDateTime.now(), LocalDateTime.now(), userId);
        entityManager.persist(insertOrder);
    }

    public void deleteOrderByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"order\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

}
