package com.justterror.auto_kit.part_model_year.boundary;

import com.justterror.auto_kit.part_model_year.entity.PartModelYear;

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
public class PartModelYearService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public PartModelYear getById(Long  id) {
        String rawQuery = String.format("FROM PartModelYear WHERE id = %d", id);
        TypedQuery<PartModelYear> query = entityManager.createQuery(rawQuery, PartModelYear.class);
        return query.getSingleResult();
    }

    public List<PartModelYear> getAll() {
        TypedQuery<PartModelYear> query = entityManager.createQuery("select p from PartModelYear p", PartModelYear.class);
        return query.getResultList();
    }

    public List<PartModelYear> getAllByModelYearId(Long modelYearId) {
        String rawQuery = String.format("FROM PartModelYear WHERE model_year_id = %d", modelYearId);
        TypedQuery<PartModelYear> query = entityManager.createQuery(rawQuery, PartModelYear.class);
        return query.getResultList();
    }

    public List<PartModelYear> getAllByPartTypeId(Long partTypeId) {
        String rawQuery = String.format("FROM PartModelYear WHERE part_type_id = %d", partTypeId);
        TypedQuery<PartModelYear> query = entityManager.createQuery(rawQuery, PartModelYear.class);
        return query.getResultList();
    }

    public List<PartModelYear> getAllByMeasureId(Long measureId) {
        String rawQuery = String.format("FROM PartModelYear WHERE measure_id = %d", measureId);
        TypedQuery<PartModelYear> query = entityManager.createQuery(rawQuery, PartModelYear.class);
        return query.getResultList();
    }

    public List<PartModelYear> getAllByOEMPartId(Long oemPartId) {
        String rawQuery = String.format("FROM PartModelYear WHERE oem_part_id = %d", oemPartId);
        TypedQuery<PartModelYear> query = entityManager.createQuery(rawQuery, PartModelYear.class);
        return query.getResultList();
    }

    public void insertNewPartModelYearTODB(long model_year_id, long part_type_id, long measure_id, long oem_part_id,
                                           int labour, int quantity) throws SQLException {
        String queryString = String.format("INSERT INTO \"part_model_year\" (model_year_id, part_type_id, measure_id, oem_part_id, labour, quantity) VALUES (%d, %d, %d, %d, %d, %d)",
                model_year_id, part_type_id, measure_id, oem_part_id, labour, quantity);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deletePartModelYearByID(Long id) throws SQLException{
        String queryString = String.format("DELETE FROM \"part_model_year\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }
}
