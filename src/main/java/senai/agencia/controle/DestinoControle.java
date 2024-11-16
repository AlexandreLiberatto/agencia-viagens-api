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

    @PostMapping
    public void cadastrarDestinoViagens(@RequestBody @Valid DestinosDTO dto){

        destinosServico.cadastrarDestino(dto);
    }

    @GetMapping
    public List<DestinosDTO> listarDestinos(){

        return destinosServico.listarDestinos();
    }

    @GetMapping("/{id}")
    public DestinosDTO buscarPorId(@PathVariable @NotNull Long id){

        return destinosServico.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DestinosDTO atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid DestinosDTO dto){
        DestinosDTO destinosAtualizado = destinosServico.atuallizarDestino(id, dto);
        return destinosAtualizado;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable @NotNull Long id){
        destinosServico.excluir(id);
    }

}
