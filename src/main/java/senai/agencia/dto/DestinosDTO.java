package senai.agencia.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinosDTO { // Classe DTO para controlar tudo que sai para o cliente e tudo que retorna

    private Long id;
    @NotBlank //valida para não ser inseridos dados nulos ou em branco
    private String destinosViagens;
    private String nome;
    private String localizacao;
    private String informacoesSobreDestino;
    private Boolean estaDispinivel;

    @DecimalMin(value = "1.0", message = "A avaliação mínima permitida é 1.0")
    @DecimalMax(value = "10.0", message = "A avaliação máxima permitida é 10.0")
    private Double avaliacao;

    private Double mediaAvaliacoes;
    private String pacotesViagens;

    @Positive // Impede que seja inserido número negativo
    private Double precoPacote;

    @Positive // Impede que seja inserido número negativo
    private Integer totalAvaliacoes;
}
