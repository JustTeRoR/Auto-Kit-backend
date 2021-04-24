package com.justterror.auto_kit.order_part_status.boundary;

import com.justterror.auto_kit.order_part_status.entity.OrderPartStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class OrderPartStatusService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public OrderPartStatus getById(long  id) {
        String rawQuery = String.format("FROM OrderPartStatus WHERE id = %d", id);
        TypedQuery<OrderPartStatus> query = entityManager.createQuery(rawQuery, OrderPartStatus.class);
        return query.getSingleResult();
    }

    public List<OrderPartStatus> getAll() {
        TypedQuery<OrderPartStatus> query = entityManager.createQuery("select p from OrderPartStatus p", OrderPartStatus.class);
        return query.getResultList();
    }

    public OrderPartStatus getByKey(String  key) {
        String rawQuery = String.format("FROM OrderPartStatus WHERE key = '%s'", key);
        TypedQuery<OrderPartStatus> query = entityManager.createQuery(rawQuery, OrderPartStatus.class);
        return query.getSingleResult();
    }
}
