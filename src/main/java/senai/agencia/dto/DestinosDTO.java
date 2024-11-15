package senai.agencia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinosDTO { // Classe DTO para controlar tudo que sai para o cliente e tudo que retorna

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
