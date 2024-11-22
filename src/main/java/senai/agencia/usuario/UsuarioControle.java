package senai.agencia.usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import senai.agencia.dto.DestinosDTO;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControle {

    private final UsuarioServico usuarioServico;

    //Registra um usuário no banco
    @PostMapping
    public ResponseEntity<DadosUsuarioCadastro> cadastrarDestinoViagens(@RequestBody @Valid DadosUsuarioCadastro dto, UriComponentsBuilder uriBuilder){
        DadosUsuarioCadastro usuarioDTO = usuarioServico.criarUsuario(dto);
        URI endereco = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(usuarioDTO);
    }
}
