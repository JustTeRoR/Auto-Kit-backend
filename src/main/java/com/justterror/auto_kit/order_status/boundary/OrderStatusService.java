package com.justterror.auto_kit.order_status.boundary;

import com.justterror.auto_kit.order_status.entity.OrderStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class OrderStatusService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public OrderStatus getById(long  id) {
        String rawQuery = String.format("FROM OrderStatus WHERE id = %d", id);
        TypedQuery<OrderStatus> query = entityManager.createQuery(rawQuery, OrderStatus.class);
        return query.getSingleResult();
    }

    public List<OrderStatus> getAll() {
        TypedQuery<OrderStatus> query = entityManager.createQuery("select p from OrderStatus p", OrderStatus.class);
        return query.getResultList();
    }

    public OrderStatus getByKey(String  key) {
        String rawQuery = String.format("FROM OrderStatus WHERE key = '%s'", key);
        TypedQuery<OrderStatus> query = entityManager.createQuery(rawQuery, OrderStatus.class);
        return query.getSingleResult();
    }
}
