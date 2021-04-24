package com.justterror.auto_kit.measure.boundary;

import com.justterror.auto_kit.measure.entity.Measure;
import com.justterror.auto_kit.model.entity.Model;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;

//TODO:: to debug this

@ApplicationScoped
@Path("/measure")
public class MeasureResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private MeasureService measureService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Measure> listAll()
    {
        logger.info("Get all measures ");
        return measureService.getAll();
    }

    @GET
    @Path("/by_measure_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Measure getById(@QueryParam("id") long id) {
        logger.info("Get measure with id = " + id);
        return measureService.getById(id);
    }

    @GET
    @Path("/by_name")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Measure> getByName(@QueryParam("name") String name) {
        logger.info("Get measure with name = " + name);
        return measureService.getAllByName(name);
    }
}
