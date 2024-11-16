package senai.agencia.controle;

import org.springframework.web.bind.annotation.*;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.servico.DestinosServico;

import java.util.List;

@RestController //indica para o Spring que é um controller
@RequestMapping("/destinos") // End point para acessar está classe no navegador ex: localhost:8080/destinos
public class DestinoControle {

    private final DestinosServico destinosServico;

    public DestinoControle(DestinosServico destinosServico) {
        this.destinosServico = destinosServico;
    }

    @PostMapping
    public void cadastrarDestinoViagens(@RequestBody DestinosDTO dto){
        destinosServico.cadastrarDestino(dto);
    }

    @GetMapping
    public List<DestinosDTO> listarDestinos(){
        return destinosServico.listarDestinos();
    }

}
