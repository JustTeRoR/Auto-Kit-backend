package com.justterror.auto_kit.model_year.boundary;

import com.justterror.auto_kit.model.boundary.ModelService;
import com.justterror.auto_kit.model.entity.Model;
import com.justterror.auto_kit.model_year.entity.ModelYear;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ModelYearService {
    @Inject
    Logger logger;

    @Inject
    ModelService modelService;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public ModelYear getById(long  id) {
        String rawQuery = String.format("FROM ModelYear WHERE id = %d", id);
        TypedQuery<ModelYear> query = entityManager.createQuery(rawQuery, ModelYear.class);
        return query.getSingleResult();
    }

    public List<ModelYear> getAll() {
        TypedQuery<ModelYear> query = entityManager.createQuery("select p from ModelYear p", ModelYear.class);
        return query.getResultList();
    }

    public List<ModelYear> getAllByUserId(long userId) {
        String rawQuery = String.format("FROM ModelYear WHERE user_id = %d", userId);
        TypedQuery<ModelYear> query = entityManager.createQuery(rawQuery, ModelYear.class);
        return query.getResultList();
    }

    public List<ModelYear> getAllByModelId(long modelId) {
        String rawQuery = String.format("FROM ModelYear WHERE model_id = %d", modelId);
        TypedQuery<ModelYear> query = entityManager.createQuery(rawQuery, ModelYear.class);
        return query.getResultList();
    }

    public void insertModelYearTODB(int year, long modelId, long user_id, String carDetailsJson, String scheduleJson) throws SQLException {
        //TODO:: TO change or remove setting of vpic_id from constant 1 + to think about setting another trimName
        ModelYear insertModelYear = new ModelYear(year, carDetailsJson, scheduleJson, modelId, 1, user_id);
        entityManager.persist(insertModelYear);
    }

    public void deleteModelYearByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"model_year\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

}
