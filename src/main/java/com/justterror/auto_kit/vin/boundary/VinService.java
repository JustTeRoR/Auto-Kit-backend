package com.justterror.auto_kit.vin.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justterror.auto_kit.make.boundary.MakeService;
import com.justterror.auto_kit.make.entity.Make;
import com.justterror.auto_kit.model.boundary.ModelService;
import com.justterror.auto_kit.model.entity.Model;
import com.justterror.auto_kit.model_year.boundary.ModelYearService;
import com.justterror.auto_kit.model_year.entity.ModelYear;
import com.justterror.auto_kit.security.Constants;
import com.justterror.auto_kit.vin.entity.Vin;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class VinService {

    @Inject
    Logger logger;

    @Inject
    MakeService makeService;

    @Inject
    ModelService modelService;

    @Inject
    ModelYearService modelYearService;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public Vin getById(long  id) {
        String rawQuery = String.format("FROM Vin WHERE id = %d", id);
        TypedQuery<Vin> query = entityManager.createQuery(rawQuery, Vin.class);
        return query.getSingleResult();
    }

    public Vin getByVin(String  vin) {
        String rawQuery = String.format("FROM Vin WHERE vin = '%s'", vin);
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

    public void deleteVinByID(long id) throws SQLException {
        String queryString = String.format("DELETE FROM \"vin\" WHERE id IN ('%d')", id);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }

    public void deleteVinByVin(String vin) throws SQLException {
        String queryString = String.format("DELETE FROM \"vin\" WHERE vin IN ('%s')", vin);
        Query query= entityManager.createNativeQuery(queryString);
        query.executeUpdate();
    }



    public String  parseRequestedVIN(String vin, long userId) throws IOException, SQLException {
        try {
            Vin searchIfExists = getByVin(vin);
            return modelYearService.getById(searchIfExists.getModelYearID()).getCarDetailsJson();
        } catch (NoResultException nre) {
            String rawUrl = String.format("https://vindecoder.p.rapidapi.com/v2.0/decode_vin?vin=%s", vin);
            URL parseVinUrl = new URL(rawUrl);

            HttpURLConnection connection = (HttpURLConnection) parseVinUrl.openConnection();
            connection.setRequestProperty("x-rapidapi-key", Constants.X_RAPIDAPI_KEY);
            connection.setRequestProperty("x-rapidapi-host", Constants.X_RAPIDAPI_HOST);
            connection.setRequestProperty("useQueryString", "true");
            connection.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String responseString = response.toString();
            //TODO:  removed from regexp first * after dot, check if now works better
            String specificationRegexp = "\"(specification)\":((\\ \"|[^}].)*.)";
            String vinRegexp = "\"(vin)\":\"((\\ \"|[^\"])*)";

            Pattern specificationPattern = Pattern.compile(specificationRegexp);
            Pattern VinPattern = Pattern.compile(vinRegexp);

            Matcher matcher = specificationPattern.matcher(responseString);
            Matcher matcher1 = VinPattern.matcher(responseString);
            if (matcher.find() && matcher1.find()) {
                String specificationStringJson = matcher.group(2);
                String vinString = matcher1.group(2);

                String makeRegexp = "\"(make)\":\"((\\ \"|[^\"])*)";
                Pattern makePattern = Pattern.compile(makeRegexp);
                Matcher makeMatcher = makePattern.matcher(specificationStringJson);
                makeMatcher.find();
                String makeName = makeMatcher.group(2);

                List<Make> make = makeService.getAllByName(makeName);
                int newMakeId = 0;
                if (make.size() == 0)
                {
                    makeService.createNewMakeInDB(makeName);
                    make = makeService.getAllByName(makeName);
                    newMakeId = makeService.getAll().size();
                }

                String modelRegexp = "\"(model)\":\"((\\ \"|[^\"])*)";
                Pattern modelPattern = Pattern.compile(modelRegexp);
                Matcher modelMatcher = modelPattern.matcher(specificationStringJson);
                modelMatcher.find();
                String modelName = modelMatcher.group(2);

                List<Model> model = modelService.getAllByName(modelName);
                int newModelId = 0;

                if (model.size() == 0) {
                    if (newMakeId != 0) {
                        modelService.insertModelTODB(modelName, newMakeId);
                    } else {
                        modelService.insertModelTODB(modelName, make.get(0).getId());
                    }
                    model = modelService.getAllByName(modelName);
                    newModelId =  modelService.getAll().size();
                }

                String yearRegexp = "\"(year)\":\"((\\ \"|[^\"])*)";
                Pattern yearPattern = Pattern.compile(yearRegexp);
                Matcher yearMatcher = yearPattern.matcher(specificationStringJson);
                //TODO:: Change setting of schedule json
                yearMatcher.find();
                int year = Integer.parseInt(yearMatcher.group(2));

                if (newModelId != 0) {
                    modelYearService.insertModelYearTODB(year, newModelId, userId, specificationStringJson, specificationStringJson);
                } else {
                    modelYearService.insertModelYearTODB(year, model.get(0).getId(), userId, specificationStringJson, specificationStringJson);
                }
                List<ModelYear> userCars = modelYearService.getAllByUserId(userId);
                ModelYear createdObject = new ModelYear();
                for (int i = 0; i < userCars.size(); i++) {
                    if (userCars.get(i).getCarDetailsJson().equals(specificationStringJson)) {
                        createdObject = userCars.get(i);
                        break;
                    }
                }

                insertNewVinTODB(vin, createdObject.getId());

                return createdObject.getCarDetailsJson();

            } else {
                return null;
            }
        }
    }
}
