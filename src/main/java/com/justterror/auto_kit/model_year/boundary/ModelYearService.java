package com.justterror.auto_kit.model_year.boundary;

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

    //TODO:: To implement method for insertion (read how to transfer json objects in such situations)

    public void deleteModelYearByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"model_year\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

}
