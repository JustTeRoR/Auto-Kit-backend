package com.justterror.auto_kit.make.boundary;

import com.justterror.auto_kit.make.entity.Make;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    @Transactional
    public void createNewMakeInDB(String name) throws SQLException {
        List<Make> allMakes = getAll();
        //TODO:: TO change or remove setting of vpic_id from constant 1
        long newId = allMakes.size() + 1;
        Make insertMake = new Make(newId, 1, name);
        entityManager.persist(insertMake);
    }
}
