package senai.agencia.servico;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.entidade.Destinos;
import senai.agencia.repositorio.DestinosRepositorio;

@Service // Informa para o Spring que é uma classe de serviços
public class DestinosServico {

    private final DestinosRepositorio destinosRepositorio;

    private final ModelMapper modelMapper;

    public DestinosServico(DestinosRepositorio destinosRepositorio, ModelMapper modelMapper) {
        this.destinosRepositorio = destinosRepositorio;
        this.modelMapper = modelMapper;
    }


    public DestinosDTO cadastrarDestino(DestinosDTO dto) {
        Destinos destinos = modelMapper.map(dto, Destinos.class);
        destinosRepositorio.save(destinos);
        return modelMapper.map(destinos, DestinosDTO.class);
    }
}
