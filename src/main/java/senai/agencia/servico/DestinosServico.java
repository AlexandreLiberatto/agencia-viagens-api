package senai.agencia.servico;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
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

    //Cadastra um novo destino no banco
    public DestinosDTO cadastrarDestino(DestinosDTO dto) {
        Destinos destinos = modelMapper.map(dto, Destinos.class);
        destinosRepositorio.save(destinos);
        return modelMapper.map(destinos, DestinosDTO.class);
    }

    //Lista todos os destinos registrados no banco
    public List<DestinosDTO> listarDestinos() {
        return destinosRepositorio.findAll().stream().map(p -> modelMapper.map(p, DestinosDTO.class)).collect(Collectors.toList());
    }

    //Método para buscar um registro por ID
    public DestinosDTO buscarPorId(Long id){
        Destinos destinos = destinosRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(destinos, DestinosDTO.class);
    }

    //Método para atualizar registro no banco
    public DestinosDTO atuallizarDestino(Long id, DestinosDTO dto) {
        Destinos destinos = modelMapper.map(dto, Destinos.class);
        destinos.setId(id);
        destinos = destinosRepositorio.save(destinos);
        return modelMapper.map(destinos, DestinosDTO.class);
    }


    //Método para excluir registro do banco
    public void excluir(Long id) {
        destinosRepositorio.deleteById(id);
    }

    //Método para pesquisar destinos por nome ou localização
    public List<DestinosDTO> pesquisarDestinos(String nome, String localizacao) {
        List<Destinos> resultados;

        if (nome != null && !nome.isEmpty()) {
            resultados = destinosRepositorio.findByNomeContainingIgnoreCase(nome);
        } else if (localizacao != null && !localizacao.isEmpty()) {
            resultados = destinosRepositorio.findByLocalizacaoContainingIgnoreCase(localizacao);
        } else {
            throw new IllegalArgumentException("Ops! Insira nome ou localização...");
        }

        return destinosRepositorio.findAll().stream().map(p -> modelMapper.map(p, DestinosDTO.class)).collect(Collectors.toList());
    }

}
