package senai.agencia.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;
import senai.agencia.usuario.Usuario;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServico {

    public String criarToken(Usuario usuario){

        try {
            Algorithm algoritmo = Algorithm.HMAC256("123");
            LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(2);

            return JWT.create()
                    .withIssuer("Agencia de Viagens")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao.toInstant(ZoneOffset.of("-03:00")))
                    .sign(algoritmo);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao criar o token", e);
        }

    }

    public String buscarUsuarioToken(String token){
        try {
            Algorithm algoritmo = Algorithm.HMAC256("123");

            return JWT.require(algoritmo)
                    .withIssuer("Agencia de Viagens")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException ex){
            throw new RuntimeException("Token incorreto!");
        }
    }
}
