package com.justterror.auto_kit.schedule_request.boundary;

import com.justterror.auto_kit.schedule_request.entity.ScheduleRequest;
import com.justterror.auto_kit.vinrange.entity.VinRange;

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
public class ScheduleRequestService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public ScheduleRequest getById(Long  id) {
        String rawQuery = String.format("SELECT * from \"schedule_request\" WHERE id = %d", id);
        TypedQuery<ScheduleRequest> query = entityManager.createQuery(rawQuery, ScheduleRequest.class);
        return query.getSingleResult();
    }

    public List<ScheduleRequest> getAll() {

        TypedQuery<ScheduleRequest> query = entityManager.createQuery("select v from ScheduleRequest v", ScheduleRequest.class);
        return query.getResultList();
    }

    public List<ScheduleRequest> getAllwithThisVinID(Long vinID) {
        String rawQuery = String.format("SELECT * from \"schedule_request\" WHERE vin_id = %d", vinID);
        TypedQuery<ScheduleRequest> query = entityManager.createQuery(rawQuery, ScheduleRequest.class);
        return query.getResultList();
    }

    public void insertNewScheduleRequestTODB(int mileage, long vinID, String ip) throws SQLException {
        String queryString = String.format("INSERT INTO \"schedule_request\" (mileage, vin_id, ip) VALUES (%d, %d, '%s')",
                mileage, vinID, ip);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteScheduleRequestByID(Long id) throws SQLException{
        String queryString = String.format("DELETE FROM \"schedule_request\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
}
