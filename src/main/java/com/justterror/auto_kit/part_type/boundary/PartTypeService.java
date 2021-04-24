package com.justterror.auto_kit.part_type.boundary;

import com.justterror.auto_kit.part_type.entity.PartType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PartTypeService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public PartType getById(Long  id) {
        String rawQuery = String.format("FROM PartType WHERE id = %d", id);
        TypedQuery<PartType> query = entityManager.createQuery(rawQuery, PartType.class);
        return query.getSingleResult();
    }

    public List<PartType> getByName(String name) {
        String rawQuery = String.format("FROM PartType WHERE name = '%s'", name);
        TypedQuery<PartType> query = entityManager.createQuery(rawQuery, PartType.class);
        return query.getResultList();
    }

    public List<PartType> getAll() {
        TypedQuery<PartType> query = entityManager.createQuery("FROM PartType p", PartType.class);
        return query.getResultList();
    }


}
