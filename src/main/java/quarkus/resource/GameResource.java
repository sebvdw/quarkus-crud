package quarkus.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import quarkus.entity.GameEntity;

@Path("/games")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class GameResource {

    @GET
    public List<GameEntity> list() {
        return GameEntity.listAll();
    }

    @GET
    @Path("/{id}")
    public GameEntity get(@PathParam("id") Long id) {
        return GameEntity.findById(id);
    }

    @POST
    @Transactional
    public Response create(GameEntity game) {
        game.persist();
        return Response.created(null).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public GameEntity update(@PathParam("id") Long id, GameEntity game) {
        GameEntity entity = GameEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.name = game.name;
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        GameEntity entity = GameEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
}
