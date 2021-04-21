package com.justterror.auto_kit.schedule.boundary;

import com.justterror.auto_kit.schedule.entity.Schedule;

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
public class ScheduleService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Schedule getById(Long  id) {
        String rawQuery = String.format("SELECT * from \"schedule\" WHERE id = %d", id);
        TypedQuery<Schedule> query = entityManager.createQuery(rawQuery, Schedule.class);
        return query.getSingleResult();
    }

    public List<Schedule> getByPartModelYearID(long  partModelYearID) {
        String rawQuery = String.format("SELECT * from \"schedule\" WHERE part_model_year_id = %d", partModelYearID);
        TypedQuery<Schedule> query = entityManager.createQuery(rawQuery, Schedule.class);
        return query.getResultList();
    }

    public List<Schedule> getByModelYearID(long  modelYearID) {
        String rawQuery = String.format("SELECT * from \"schedule\" WHERE model_year_id = %d", modelYearID);
        TypedQuery<Schedule> query = entityManager.createQuery(rawQuery, Schedule.class);
        return query.getResultList();
    }

    public List<Schedule> getAll() {

        TypedQuery<Schedule> query = entityManager.createQuery("select s from Schedule s", Schedule.class);
        return query.getResultList();
    }

    public void insertNewScheduleTODB(long mileage,long partModelYearId, long modelYearId) throws SQLException {
        String queryString = String.format("INSERT INTO \"schedule\" (mileage, part_model_year_id, model_year_id) VALUES (%d, %d, %d)",
                mileage, partModelYearId, modelYearId);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteScheduleByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"schedule\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteScheduleByPartModelYearID(long partModelYearID) throws SQLException {
        String queryString = String.format("DELETE FROM \"schedule\" WHERE part_model_year_id IN ('%d')", partModelYearID);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
    public void deleteScheduleByModelYearID(long modelYearID) throws SQLException {
        String queryString = String.format("DELETE FROM \"schedule\" WHERE model_year_id IN ('%d')", modelYearID);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

}
