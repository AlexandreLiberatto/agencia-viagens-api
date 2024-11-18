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

    //pega um destino pelo ID, e mostra informações detalhadas sobre um destino
    //específico.
    public DestinosDTO buscarPorId(Long id){
        Destinos destinos = destinosRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(destinos, DestinosDTO.class);
    }

    // Método para atualizar registro no banco
    public DestinosDTO atualizarDestino(Long id, DestinosDTO dto) {
        Destinos destinoExistente = destinosRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Destino com ID " + id + " não encontrado"));

        modelMapper.map(dto, destinoExistente);
        destinoExistente.setId(id);

        destinoExistente = destinosRepositorio.save(destinoExistente);
        return modelMapper.map(destinoExistente, DestinosDTO.class);
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

    // Método para inserir uma avaliação e alterar a média das avaliações
    public DestinosDTO avaliarDestino(Long id, double novaAvaliacao) {
        if (novaAvaliacao < 1.0 || novaAvaliacao > 10.0) {
            throw new IllegalArgumentException("A avaliação deve estar entre 1.0 e 10.0");
        }

        Destinos destino = destinosRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Destino não encontrado"));

        // Atualizando média
        double avaliacaoExistente = destino.getMediaAvaliacoes() != null ? destino.getMediaAvaliacoes() : 0.0;
        int totalAvaliacoes = destino.getTotalAvaliacoes() != null ? destino.getTotalAvaliacoes() : 0;

        double novaMedia = ((avaliacaoExistente * totalAvaliacoes) + novaAvaliacao) / (totalAvaliacoes + 1);

        // Atualiza avaliações
        destino.setMediaAvaliacoes(novaMedia);
        destino.setTotalAvaliacoes(totalAvaliacoes + 1);

        // Salva alterações
        destinosRepositorio.save(destino);

        return modelMapper.map(destino, DestinosDTO.class);
    }

}
