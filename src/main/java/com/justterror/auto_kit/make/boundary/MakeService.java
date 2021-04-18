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

    @PersistenceContext(name="Make")
    private EntityManager entityManager;

    public List<Make> getAll() {
        TypedQuery<Make> query = entityManager.createQuery("select m from Make m", Make.class);
        return query.getResultList();
    }

    public Make getById(Long searchID) {
        TypedQuery<Make> query = entityManager.createQuery("select m from Make m where  m.ID =" + searchID, Make.class);
        return query.getSingleResult();
    }

}
