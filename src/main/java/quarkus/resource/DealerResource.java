package quarkus.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import quarkus.entity.DealerEntity;

@Path("/dealers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class DealerResource {

    @GET
    public List<DealerEntity> list() {
        return DealerEntity.listAll();
    }

    @GET
    @Path("/{id}")
    public DealerEntity get(@PathParam("id") Long id) {
        return DealerEntity.findById(id);
    }

    @POST
    @Transactional
    public Response create(DealerEntity dealer) {
        dealer.persist();
        return Response.created(null).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public DealerEntity update(@PathParam("id") Long id, DealerEntity dealer) {
        DealerEntity entity = DealerEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.name = dealer.name;
        entity.employeeId = dealer.employeeId;
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        DealerEntity entity = DealerEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
}
