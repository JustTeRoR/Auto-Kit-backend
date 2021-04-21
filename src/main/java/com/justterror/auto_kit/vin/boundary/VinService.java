package com.justterror.auto_kit.vin.boundary;

import com.justterror.auto_kit.vin.entity.Vin;

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
public class VinService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Vin getById(Long  id) {
        String rawQuery = String.format("SELECT * from \"vin\" WHERE id = %d", id);
        TypedQuery<Vin> query = entityManager.createQuery(rawQuery, Vin.class);
        return query.getSingleResult();
    }

    public Vin getByVin(String  vin) {
        String rawQuery = String.format("SELECT * from \"vin\" WHERE vin = '%s'", vin);
        TypedQuery<Vin> query = entityManager.createQuery(rawQuery, Vin.class);
        return query.getSingleResult();
    }

    public List<Vin> getAll() {

        TypedQuery<Vin> query = entityManager.createQuery("select v from Vin v", Vin.class);
        return query.getResultList();
    }

    public void insertNewVinTODB(String vin, long modelYearId) throws SQLException {
        String queryString = String.format("INSERT INTO \"vin\" (vin, model_year_id) VALUES ('%s', %d)",
                vin, modelYearId);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteVinByID(Long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"vin\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteVinByVin(String vin) throws SQLException {
        String queryString = String.format("DELETE FROM \"vin\" WHERE vin IN ('%s')", vin);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
}
