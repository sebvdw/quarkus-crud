package quarkus.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import quarkus.model.AuthRequest;
import quarkus.model.AuthResponse;
import quarkus.model.User;
import quarkus.utils.PBKDF2Encoder;
import quarkus.utils.TokenUtils;

@Path("/auth")
public class AuthResource {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "quarkus.jwt.duration")
    public Long duration;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @PermitAll
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest authRequest) {
        User u = User.findByUsername(authRequest.username);
        if (
            u != null &&
            u.password.equals(passwordEncoder.encode(authRequest.password))
        ) {
            try {
                return Response.ok(
                    new AuthResponse(
                        TokenUtils.generateToken(
                            u.username,
                            u.roles,
                            duration,
                            issuer
                        )
                    )
                ).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
