package com.justterror.auto_kit.model.boundary;

import com.justterror.auto_kit.make.boundary.MakeService;
import com.justterror.auto_kit.make.entity.Make;
import com.justterror.auto_kit.model.entity.Model;

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
public class ModelService {

    @Inject
    Logger logger;

    @Inject
    MakeService makeService;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Model getById(long  id) {
        String rawQuery = String.format("FROM Model WHERE id = %d", id);
        TypedQuery<Model> query = entityManager.createQuery(rawQuery, Model.class);
        return query.getSingleResult();
    }

    public List<Model> getAll() {
        TypedQuery<Model> query = entityManager.createQuery("select p from Model p", Model.class);
        return query.getResultList();
    }

    public List<Model> getAllByName(String name) {
        String rawQuery = String.format("FROM Model WHERE name = '%s'", name);
        TypedQuery<Model> query = entityManager.createQuery(rawQuery, Model.class);
        return query.getResultList();
    }

    public List<Model> getAllByMakeID(long makeId) {
        String rawQuery = String.format("FROM Model WHERE make_id = %d", makeId);
        TypedQuery<Model> query = entityManager.createQuery(rawQuery, Model.class);
        return query.getResultList();
    }

    public void insertModelTODB(String nameModel, long  makeId) throws SQLException {
        //TODO:: TO change or remove setting of vpic_id from constant 1 + to think about setting another trimName
        List<Model> allModels = getAll();
        long newId = allModels.size() + 1;
        Model insertModel = new Model(newId, makeId, 1, nameModel, nameModel);
        entityManager.persist(insertModel);
    }

    public void deleteModelByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"model\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
}
