package senai.agencia.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //declara que é uma indentidade
@Table(name = "usuario") //declara que é uma tabela
@Data // gera getters e setters
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id //mostra pro spring que isso é um id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera automaticamente o id
    private Long id;
    private String login;
    private  String password;
}
