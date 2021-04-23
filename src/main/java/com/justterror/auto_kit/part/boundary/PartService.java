package com.justterror.auto_kit.part.boundary;

import com.justterror.auto_kit.part.entity.Part;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

//TODO:: To debug this

@Stateless
public class PartService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Part getById(long  id) {
        String rawQuery = String.format("FROM Part WHERE id = %d", id);
        TypedQuery<Part> query = entityManager.createQuery(rawQuery, Part.class);
        return query.getSingleResult();
    }

    public List<Part> getAll() {
        TypedQuery<Part> query = entityManager.createQuery("select p from PartModelYear p", Part.class);
        return query.getResultList();
    }

    public List<Part> getAllByMeasureId(long measureId) {
        String rawQuery = String.format("FROM Part WHERE measure_id = %d", measureId);
        TypedQuery<Part> query = entityManager.createQuery(rawQuery, Part.class);
        return query.getResultList();
    }

    public List<Part> getAllByPartTypeId(long partTypeId) {
        String rawQuery = String.format("FROM Part WHERE part_type_id = %d", partTypeId);
        TypedQuery<Part> query = entityManager.createQuery(rawQuery, Part.class);
        return query.getResultList();
    }

    public List<Part> getAllByMakeId(long makeId) {
        String rawQuery = String.format("FROM Part WHERE make_id = %d", makeId);
        TypedQuery<Part> query = entityManager.createQuery(rawQuery, Part.class);
        return query.getResultList();
    }

    public List<Part> getAllBySerialNumber(String serialNumber) {
        String rawQuery = String.format("FROM Part WHERE serial_number = '%s'", serialNumber);
        TypedQuery<Part> query = entityManager.createQuery(rawQuery, Part.class);
        return query.getResultList();
    }

    //TODO:: to check what should be placed instead of %d for lastDeliveryDate on line 64
    public void insertNewPartTODB(int quantity, long measureId, long makeId, long partTypeId, boolean isOEM, BigDecimal lasPurchasePrice,
                                           Date lastDeliveryDate, String SerialNumber) throws SQLException {
        String queryString = String.format("INSERT INTO \"part\" (quantity, measure_id, make_id, part_type_id, is_oem, last_purchase_price," +
                        "last_delivery_time, serial_number) VALUES (%d, %d, %d, %d, %b, %f, %d, %s)",
                quantity, measureId, makeId, partTypeId, isOEM, lasPurchasePrice, lastDeliveryDate, SerialNumber);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deletePartByID(long id) throws SQLException{
        String queryString = String.format("DELETE FROM \"part\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deletePartBySerialNumber(String serialNumber) throws SQLException{
        String queryString = String.format("DELETE FROM \"part\" WHERE serial_number IN ('%s')", serialNumber);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }


}
