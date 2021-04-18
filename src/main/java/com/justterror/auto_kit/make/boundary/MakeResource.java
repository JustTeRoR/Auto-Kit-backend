package com.justterror.auto_kit.make.boundary;

import com.justterror.auto_kit.make.entity.Make;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/make")
public class MakeResource {
    @Inject
    Logger logger;

    @Inject
    private MakeService makeService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Make> getAllMake() {
        logger.info("Get all makes from MAKE table");
        return makeService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Make getMakeById(@PathParam("id") Long id) {
        logger.info("Get  make from MAKE table by Id - " + id.toString());
        return makeService.getById(id);
    }

}
