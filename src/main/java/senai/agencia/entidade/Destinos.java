package senai.agencia.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //declara que é uma indentidade
@Table(name = "destinos") //declara que é uma tabela
@Data // gera getters e setters
@AllArgsConstructor
@NoArgsConstructor
public class Destinos {

    @Id //mostra pro spring que isso é um id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera automaticamente o id
    private Long id;
    private String destinosViagens;
    private String nome;
    private String localizacao;
    private String informacoesSobreDestino;
    private Boolean destinosDispiniveis;
    private Double avaliacoes;
    private Double mediaAvaliacoes;
    private Double pacotesViagens;
    private Double precoPacote;

}
