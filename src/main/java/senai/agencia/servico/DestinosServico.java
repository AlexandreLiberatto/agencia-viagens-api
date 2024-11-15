package senai.agencia.servico;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senai.agencia.repositorio.DestinosRepositorio;

@Service // Informa para o Spring que é uma classe de serviços
@RequiredArgsConstructor
public class DestinosServico {

    private final DestinosRepositorio destinosRepositorio;

    public DestinosServico(DestinosRepositorio destinosRepositorio) {
        this.destinosRepositorio = destinosRepositorio;
    }
}
