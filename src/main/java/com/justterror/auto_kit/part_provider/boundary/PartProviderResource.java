package com.justterror.auto_kit.part_provider.boundary;

import com.justterror.auto_kit.part_provider.entity.PartProvider;

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
@Path("/part_provider")
public class PartProviderResource {
    @Inject
    Logger logger;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private PartProviderService partProviderService;

    @GET
    @Path("/all")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartProvider> listAll()
    {
        logger.info("Get all part providers from part_provider table");
        return partProviderService.getAll();
    }

    @GET
    @Path("/by_part_provider_id")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public PartProvider getById(@QueryParam("id") long id) {
        logger.info("Get part provider with id = " + id);
        return partProviderService.getById(id);
    }

    @GET
    @Path("/by_name")
    @RolesAllowed({USER, ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public List<PartProvider> getByPartIdField(@QueryParam("name") String name) {
        logger.info("Get all part providers from part_provider table with name = " + name);
        return partProviderService  .getByName(name);
    }
}
