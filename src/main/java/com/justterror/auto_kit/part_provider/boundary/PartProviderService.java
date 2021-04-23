package com.justterror.auto_kit.part_provider.boundary;

import com.justterror.auto_kit.part_provider.entity.PartProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PartProviderService {
    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public PartProvider getById(Long  id) {
        String rawQuery = String.format("FROM PartProvider WHERE id = %d", id);
        TypedQuery<PartProvider> query = entityManager.createQuery(rawQuery, PartProvider.class);
        return query.getSingleResult();
    }

    public List<PartProvider> getByName(String name) {
        String rawQuery = String.format("FROM PartProvider WHERE name = '%s'", name);
        TypedQuery<PartProvider> query = entityManager.createQuery(rawQuery, PartProvider.class);
        return query.getResultList();
    }

    public List<PartProvider> getAll() {
        TypedQuery<PartProvider> query = entityManager.createQuery("FROM PartProvider p", PartProvider.class);
        return query.getResultList();
    }
}
