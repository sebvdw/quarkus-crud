package quarkus.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import quarkus.entity.GameEntity;
import quarkus.entity.PlayerEntity;

@Path("/players")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PlayerResource {

    @GET
    public List<PlayerEntity> list() {
        return PlayerEntity.listAll();
    }

    @GET
    @Path("/{id}")
    public PlayerEntity get(@PathParam("id") Long id) {
        return PlayerEntity.findById(id);
    }

    @POST
    @Transactional
    public Response create(PlayerEntity player) {
        // Check if the associated game exists
        if (player.game != null && player.game.id != null) {
            GameEntity game = GameEntity.findById(player.game.id);
            if (game == null) {
                throw new NotFoundException("Game not found");
            }
            player.game = game;
        }
        player.persist();
        return Response.created(null).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public PlayerEntity update(@PathParam("id") Long id, PlayerEntity player) {
        PlayerEntity entity = PlayerEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException("Player not found");
        }
        entity.name = player.name;

        // Update the associated game if provided
        if (player.game != null && player.game.id != null) {
            GameEntity game = GameEntity.findById(player.game.id);
            if (game == null) {
                throw new NotFoundException("Game not found");
            }
            entity.game = game;
        }

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        PlayerEntity entity = PlayerEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException("Player not found");
        }
        entity.delete();
    }

    @GET
    @Path("/game/{gameId}")
    public List<PlayerEntity> getPlayersByGame(
        @PathParam("gameId") Long gameId
    ) {
        return PlayerEntity.list("game.id", gameId);
    }
}
