package senai.agencia.controle;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.servico.DestinosServico;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.servico.DestinosServico;

@RestController //indica para o Spring que é um controller
@RequestMapping("/destinos") // End point para acessar esta classe no navegador ex: localhost:8080/destinos
public class DestinoControle {

    private final DestinosServico destinosServico;

    public DestinoControle(DestinosServico destinosServico) {

        this.destinosServico = destinosServico;
    }

    //Registra um destino no banco
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DestinosDTO> cadastrarDestinoViagens(@RequestBody @Valid DestinosDTO dto, UriComponentsBuilder uriBuilder){
        DestinosDTO destinosDTO = destinosServico.cadastrarDestino(dto);
        URI endereco = uriBuilder.path("/destinos/{id}").buildAndExpand(destinosDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(destinosDTO);
    }

    //Lista todos os destinos registrados no banco
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Page<DestinosDTO>> listarDestinos(@PageableDefault(size = 10) Pageable paginacao) {
        Page<DestinosDTO> destinos = destinosServico.listarDestinos(paginacao);
        return ResponseEntity.ok(destinos);
    }

    //pega um destino pelo ID, e mostra informações detalhadas sobre um destino
    //específico.
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<DestinosDTO> buscarPorId(@PathVariable @NotNull Long id){
        DestinosDTO destinosDTO = destinosServico.buscarPorId(id);
        return ResponseEntity.ok(destinosDTO);
    }

    //Atualiza um registro no banco
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DestinosDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid DestinosDTO dto) {
        DestinosDTO destinosatualizado = destinosServico.atualizarDestino(id, dto);
        return ResponseEntity.ok(destinosatualizado);
    }


    //Exclui um registro do banco
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id){
        destinosServico.excluir(id);
        return ResponseEntity.noContent().build();
    }

    //Pega um destino por nome ou localização
    @GetMapping("/pesquisar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<DestinosDTO>> pesquisarDestinos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {

        List<DestinosDTO> destinos = destinosServico.pesquisarDestinos(nome, localizacao);

        if (destinos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());  // Retorna uma lista vazia se não encontrar destinos
        }
        return ResponseEntity.ok(destinos);  // Retorna os destinos com status 200 OK
    }


    //EndPoint para inserir uma avaliação e alterar a média das avaliações
    @PostMapping("/{id}/avaliar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<DestinosDTO> avaliarDestino(
            @PathVariable @NotNull Long id,
            @RequestParam @DecimalMin("1.0") @DecimalMax("10.0") double nota) {

        try {
            DestinosDTO destinoAtualizado = destinosServico.avaliarDestino(id, nota);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(destinoAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }


}
