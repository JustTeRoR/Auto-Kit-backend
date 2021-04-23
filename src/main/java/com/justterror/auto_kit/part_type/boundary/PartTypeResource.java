package com.justterror.auto_kit.part_type.boundary;

import com.justterror.auto_kit.part_type.entity.PartType;

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

@ApplicationScoped
@Path("/part_type")
public class PartTypeResource {

    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private PartTypeService partTypeService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartType> listAll()
    {
        logger.info("Get all part types from part_type table");
        return partTypeService.getAll();
    }

    @GET
    @Path("/by_part_type_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public PartType getById(@QueryParam("id") long id) {
        logger.info("Get part type with id = " + id);
        return partTypeService.getById(id);
    }

    @GET
    @Path("/by_name")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartType> getByPartIdField(@QueryParam("name") String name) {
        logger.info("Get all part types from part_type table with name = " + name);
        return partTypeService.getByName(name);
    }
}
