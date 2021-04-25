package com.justterror.auto_kit.make.boundary;

import com.justterror.auto_kit.make.entity.Make;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class MakeService {
    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Make getById(long  id) {
        String rawQuery = String.format("FROM Make WHERE id = %d", id);
        TypedQuery<Make> query = entityManager.createQuery(rawQuery, Make.class);
        return query.getSingleResult();
    }

    public List<Make> getAll() {
        TypedQuery<Make> query = entityManager.createQuery("select p from Make p", Make.class);
        return query.getResultList();
    }

    public List<Make> getAllByName(String name) {
        String rawQuery = String.format("FROM Make WHERE name = '%s'", name);
        TypedQuery<Make> query = entityManager.createQuery(rawQuery, Make.class);
        return query.getResultList();
    }

    public List<Make> getAllByVpicId(long vpicId) {
        String rawQuery = String.format("FROM Make WHERE vpic_id = %d", vpicId);
        TypedQuery<Make> query = entityManager.createQuery(rawQuery, Make.class);
        return query.getResultList();
    }
}
