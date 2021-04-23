package com.justterror.auto_kit.replaceability.boundary;

import com.justterror.auto_kit.replaceability.entity.Replaceablility;

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
public class ReplaceablilityService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Replaceablility getById(Long  id) {
        String rawQuery = String.format("SELECT * from \"replaceability\" WHERE id = %d", id);
        TypedQuery<Replaceablility> query = entityManager.createQuery(rawQuery, Replaceablility.class);
        return query.getSingleResult();
    }

    public List<Replaceablility> getByPartModelYearID(long  partModelYearID) {
        String rawQuery = String.format("SELECT * from \"replaceability\" WHERE part_model_year_id = %d", partModelYearID);
        TypedQuery<Replaceablility> query = entityManager.createQuery(rawQuery, Replaceablility.class);
        return query.getResultList();
    }

    public List<Replaceablility> getByPartID(long  partId) {
        String rawQuery = String.format("SELECT * from \"replaceability\" WHERE part_id = %d", partId);
        TypedQuery<Replaceablility> query = entityManager.createQuery(rawQuery, Replaceablility.class);
        return query.getResultList();
    }

    public List<Replaceablility> getAll() {

        TypedQuery<Replaceablility> query = entityManager.createQuery("select s from Replaceablility s", Replaceablility.class);
        return query.getResultList();
    }

    public void insertNewReplaceabilityTODB(long partId,long partModelYearId) throws SQLException {
        String queryString = String.format("INSERT INTO \"replaceability\" (part_id, part_model_year_id) VALUES (%d, %d)",
                partId, partModelYearId);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteReplaceabilityByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"replaceability\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
}