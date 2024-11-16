package senai.agencia.controle;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    public List<DestinosDTO> listarDestinos(){

        return destinosServico.listarDestinos();
    }

    //pega um destino pelo ID
    @GetMapping("/{id}")
    public DestinosDTO buscarPorId(@PathVariable @NotNull Long id){

        return destinosServico.buscarPorId(id);
    }

    //Atualiza um registro no banco
    @PutMapping("/{id}")
    public DestinosDTO atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid DestinosDTO dto){
        DestinosDTO destinosAtualizado = destinosServico.atuallizarDestino(id, dto);
        return destinosAtualizado;
    }

    //Exclui um registro do banco
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable @NotNull Long id){
        destinosServico.excluir(id);
    }

    //Pega um destino por nome ou localização
    @GetMapping("/pesquisar")
    public List<DestinosDTO> pesquisarDestinos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {

        return destinosServico.pesquisarDestinos(nome, localizacao);
    }

}
