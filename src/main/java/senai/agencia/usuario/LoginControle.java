package senai.agencia.usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginControle {

    private final AuthenticationManager autenticador;

    @PostMapping
    public ResponseEntity<?> validacaoCredenciaisUsuario(@RequestBody @Valid CredenciaisUsuarioDTO credenciais){
        //
    }
}
