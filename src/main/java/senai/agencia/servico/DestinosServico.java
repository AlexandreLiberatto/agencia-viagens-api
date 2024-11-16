package senai.agencia.servico;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.entidade.Destinos;
import senai.agencia.repositorio.DestinosRepositorio;

import java.util.List;
import java.util.stream.Collectors;

import static senai.agencia.repositorio.DestinosRepositorio.*;

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

    public List<DestinosDTO> listarDestinos() {
        return destinosRepositorio.findAll().stream().map(p -> modelMapper.map(p, DestinosDTO.class)).collect(Collectors.toList());
    }

    public DestinosDTO buscarPorId(Long id){
        Destinos destinos = destinosRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(destinos, DestinosDTO.class);
    }

}
