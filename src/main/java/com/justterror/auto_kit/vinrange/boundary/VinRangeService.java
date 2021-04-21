package com.justterror.auto_kit.vinrange.boundary;

import com.justterror.auto_kit.vinrange.entity.VinRange;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class VinRangeService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public VinRange getById(String  id) {
        String rawQuery = "select v from VinRange v where id = '" + id + "'";
        TypedQuery<VinRange> query = entityManager.createQuery(rawQuery, VinRange.class);
        return query.getSingleResult();
    }

    public List<VinRange> getAll() {

        TypedQuery<VinRange> query = entityManager.createQuery("select v from VinRange v", VinRange.class);
        return query.getResultList();
    }


    public void insertNewVinRangeTODB(String id, String modelYearId, String vinMask) {
        String queryString = String.format("INSERT INTO \"vin_range\" (id, model_year_id, vin_mask) VALUES ('%s', '%s', '%s')",
               id,modelYearId, vinMask);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
        //TODO:: Обработать эксепшены
    }
}
