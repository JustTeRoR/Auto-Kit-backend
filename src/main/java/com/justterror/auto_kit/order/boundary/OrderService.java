package com.justterror.auto_kit.order.boundary;


import com.justterror.auto_kit.order.entity.Order;

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

    public List<Order> getAllByStatusId(long statusId) {
        String rawQuery = String.format("FROM Order WHERE order_status_id = %d", statusId);
        TypedQuery<Order> query = entityManager.createQuery(rawQuery, Order.class);
        return query.getResultList();
    }

    public void createOrderTODB(long orderStatusId, BigDecimal price, long userId) throws SQLException {
        Date currentTime = new Date();
        String queryString = String.format("INSERT INTO \"order\" (order_status_id, price, creation_date, change_date, user_id) " +
                        "VALUES (%d, %f, %tD, %tD, %d)",
                orderStatusId, price, currentTime, currentTime, userId);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteOrderByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"order\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

}
