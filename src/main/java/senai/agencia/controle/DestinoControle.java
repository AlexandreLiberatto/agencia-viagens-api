package senai.agencia.controle;


import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.servico.DestinosServico;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.servico.DestinosServico;

@RestController //indica para o Spring que é um controller
@RequestMapping("/destinos") // End point para acessar está classe no navegador ex: localhost:8080/destinos
public class DestinoControle {

    private final DestinosServico destinosServico;

    public DestinoControle(DestinosServico destinosServico) {

        this.destinosServico = destinosServico;
    }

    //Registra um destino no banco
    @PostMapping
    public void cadastrarDestinoViagens(@RequestBody @Valid DestinosDTO dto){

        destinosServico.cadastrarDestino(dto);
    }

    //Lista todos os destinos registrados no banco
    @GetMapping
    public Page<DestinosDTO> listarDestinos(@PageableDefault(size = 10)Pageable paginacao){

        return destinosServico.listarDestinos(paginacao);
    }

    //pega um destino pelo ID, e mostra informações detalhadas sobre um destino
    //específico.
    @GetMapping("/{id}")
    public DestinosDTO buscarPorId(@PathVariable @NotNull Long id){

        return destinosServico.buscarPorId(id);
    }

    //Atualiza um registro no banco
    @PutMapping("/{id}")
    public DestinosDTO atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid DestinosDTO dto) {
        return destinosServico.atualizarDestino(id, dto);
    }


    //Exclui um registro do banco
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id){
        destinosServico.excluir(id);
        return ResponseEntity.noContent().build();
    }

    //Pega um destino por nome ou localização
    @GetMapping("/pesquisar")
    public List<DestinosDTO> pesquisarDestinos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {

        return destinosServico.pesquisarDestinos(nome, localizacao);
    }

    //EndPoint para inserir uma avaliação e alterar a média das avaliações
    @PostMapping("/{id}/avaliar")
    public DestinosDTO avaliarDestino(
            @PathVariable @NotNull Long id,
            @RequestParam @DecimalMin("1.0") @DecimalMax("10.0") double nota) {

        return destinosServico.avaliarDestino(id, nota);
    }

}
