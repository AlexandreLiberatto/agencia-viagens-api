package senai.agencia.usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.agencia.config.TokenServico;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginControle {

    private final AuthenticationManager autenticador;
    private final TokenServico tokenServico;

    @PostMapping
    public ResponseEntity<String> validacaoCredenciaisUsuario(@RequestBody @Valid CredenciaisUsuarioDTO credenciais) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(credenciais.getLogin(), credenciais.getPassword());
        Authentication autenticacao = autenticador.authenticate(token);

        return ResponseEntity.ok(tokenServico.criarToken((Usuario) autenticacao.getPrincipal()));
    }
}
