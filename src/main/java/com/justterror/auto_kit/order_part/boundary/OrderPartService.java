package com.justterror.auto_kit.order_part.boundary;

import com.justterror.auto_kit.order_part.entity.OrderPart;

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

//TODO:: To debug this

@Stateless
public class OrderPartService {

    @Inject
    Logger logger;

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

    public void insertNewOrderPartTODB(long orderId, long orderPartStatusId, long partProviderId, BigDecimal purchasePrice, BigDecimal price,
                                  BigDecimal labourPrice, int count, long partId) throws SQLException {
        String queryString = String.format("INSERT INTO \"order_part\" (order_id, order_part_status_id, part_provider_id, purchase_price, price, " +
                        "labour_price, count, part_id) VALUES (%d, %d, %d, %f, %f, %f, %d, %d)",
                orderId, orderPartStatusId, partProviderId, purchasePrice, price, labourPrice, count, partId);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteOrderPartByID(long id) throws SQLException{
        String queryString = String.format("DELETE FROM \"order_part\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteOrderPartByOrderId(long orderId) throws SQLException{
        String queryString = String.format("DELETE FROM \"order_part\" WHERE order_id IN ('%d')", orderId);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
}
