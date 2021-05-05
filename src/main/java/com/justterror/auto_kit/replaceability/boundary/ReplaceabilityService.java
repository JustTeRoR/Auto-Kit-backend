package com.justterror.auto_kit.replaceability.boundary;

import com.justterror.auto_kit.part.entity.Part;
import com.justterror.auto_kit.replaceability.entity.Replaceability;

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
public class ReplaceabilityService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Replaceability getById(Long  id) {
        String rawQuery = String.format("FROM Replaceability WHERE id = %d", id);
        TypedQuery<Replaceability> query = entityManager.createQuery(rawQuery, Replaceability.class);
        return query.getSingleResult();
    }

    public List<Replaceability> getByPartModelYearID(long  partModelYearID) {
        String rawQuery = String.format("FROM Replaceability WHERE part_model_year_id = %d", partModelYearID);
        TypedQuery<Replaceability> query = entityManager.createQuery(rawQuery, Replaceability.class);
        return query.getResultList();
    }

    public List<Object[]> getAllReplacementsForPartWithId(long  partId1) {
        String rawQuery = String.format("FROM Replaceability WHERE part_id1 = %d", partId1);
        TypedQuery<Replaceability> query = entityManager.createQuery(rawQuery, Replaceability.class);
        List<Replaceability> analogNotes = query.getResultList();
        String searchCriteria = "";

        for (int i = 0; i < analogNotes.size(); i++) {
            searchCriteria = searchCriteria.concat(String.valueOf(analogNotes.get(i).getPartId2()));
            if (i != analogNotes.size() - 1) {
                searchCriteria = searchCriteria.concat(",");
            }
        }
        String rawPartsQuery = String.format("select p.id, p.quantity, p.measure_id, me.name as measure_name, p.make_id, ma.name as make_name, " +
                "p.part_type_id, pt.name as part_type_name, p.is_oem, p.last_purchase_price, p.serial_number, p.last_delivery_time from part p " +
                "inner join measure me on p.measure_id = me.id inner join make ma on p.make_id = ma.id inner join part_type pt on p.part_type_id=pt.id " +
                "where p.id IN (%s)", searchCriteria);
        Query partsQuery = entityManager.createNativeQuery(rawPartsQuery);
        return partsQuery.getResultList();
    }

    public List<Replaceability> getAll() {

        TypedQuery<Replaceability> query = entityManager.createQuery("select s from Replaceability s", Replaceability.class);
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
