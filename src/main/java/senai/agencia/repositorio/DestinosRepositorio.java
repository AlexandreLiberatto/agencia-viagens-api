package senai.agencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepositori tras os métodos de CRUD integrados
import senai.agencia.entidade.Destinos;

import java.util.List;

public interface DestinosRepositorio extends JpaRepository<Destinos, Long> {

    //  Pesquisa por nome sem diferenciar maiúsculas e minúsculas
    List<Destinos> findByNomeContainingIgnoreCase(String nome);

    // Pesquisa por localização sem diferenciar maiúsculas e minúsculas
    List<Destinos> findByLocalizacaoContainingIgnoreCase(String localizacao);
}
