package senai.agencia.controle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.servico.DestinosServico;

@RestController //indica para o Spring que é um controller
@RequestMapping(name = "/destinos") // End point para acessar está classe no navegador ex: localhost:8080/destinos
@RequiredArgsConstructor
public class DestinoControle {

    private final DestinosServico destinosServico;

    @PostMapping
    public void cadastrarDestinoViagens(@RequestBody DestinosDTO dto){
        destinosServico.cadastrarDestino();
    }


    //contrutor
    public DestinoControle(DestinosServico destinosServico) {
        this.destinosServico = destinosServico;
    }
}
