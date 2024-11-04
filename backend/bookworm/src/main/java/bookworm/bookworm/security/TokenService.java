package bookworm.bookworm.security;

import bookworm.bookworm.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateUserToken(User userToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("bookworm")
                    .withSubject(userToken.getEmail())
                    .withClaim("roles", userToken.getAuthorities().stream()
                            .map(authority -> authority.getAuthority())
                            .collect(Collectors.toList()))
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 dia de expiração
                    .sign(algorithm);

            return token;

        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("bookworm")
                    .build()
                    .verify(token);

            return decodedJWT.getSubject(); // Retorna o email do usuário, mas você pode modificar para retornar mais informações se desejar.

        } catch (Exception e) {
            throw new RuntimeException("Error validating JWT token", e);
        }
    }
}
