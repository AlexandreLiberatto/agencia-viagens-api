package senai.agencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepositori tras os métodos de CRUD integrados
import senai.agencia.entidade.Destinos;

public interface DestinosRepositorio extends JpaRepository<Destinos, Long> {
}
