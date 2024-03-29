package com.justterror.auto_kit.part.boundary;

import com.justterror.auto_kit.part.entity.Part;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
        TypedQuery<Part> query = entityManager.createQuery("select p from Part p", Part.class);
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

    public List<Object[]> getAllBySerialNumber(String serialNumber) {
        String rawQuery = String.format("select p.id, p.quantity, p.measure_id, me.name as measure_name, p.make_id, ma.name as make_name, " +
                "p.part_type_id, pt.name as part_type_name, p.is_oem, p.last_purchase_price, p.serial_number, p.last_delivery_time from part p " +
                "inner join measure me on p.measure_id = me.id inner join make ma on p.make_id = ma.id inner join part_type pt on p.part_type_id=pt.id " +
                "where serial_number like ('%s",serialNumber + "%')");
        Query query = entityManager.createNativeQuery(rawQuery);
        return query.getResultList();
    }

    public void insertNewPartTODB(int quantity, long measureId, long makeId, long partTypeId, boolean isOEM, BigDecimal lasPurchasePrice,
                                  String SerialNumber, String lastDeliveryDate) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Part insertPart = new Part(quantity,measureId,makeId,partTypeId,isOEM,lasPurchasePrice,LocalDateTime.parse(lastDeliveryDate,formatter),SerialNumber);
        entityManager.persist(insertPart);
    }

    public void deletePartByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"part\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deletePartBySerialNumber(String serialNumber) throws SQLException {
        String queryString = String.format("DELETE FROM \"part\" WHERE serial_number IN ('%s')", serialNumber);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }


}
