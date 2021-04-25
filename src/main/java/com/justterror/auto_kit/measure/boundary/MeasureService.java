package com.justterror.auto_kit.measure.boundary;

import com.justterror.auto_kit.measure.entity.Measure;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class MeasureService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Measure getById(long  id) {
        String rawQuery = String.format("FROM Measure WHERE id = %d", id);
        TypedQuery<Measure> query = entityManager.createQuery(rawQuery, Measure.class);
        return query.getSingleResult();
    }

    public List<Measure> getAll() {
        TypedQuery<Measure> query = entityManager.createQuery("select p from Measure p", Measure.class);
        return query.getResultList();
    }

    public List<Measure> getAllByName(String name) {
        String rawQuery = String.format("FROM Measure WHERE name = '%s'", name);
        TypedQuery<Measure> query = entityManager.createQuery(rawQuery, Measure.class);
        return query.getResultList();
    }
}
