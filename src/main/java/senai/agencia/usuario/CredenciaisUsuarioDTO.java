package senai.agencia.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredenciaisUsuarioDTO {
    private String login;
    private String password;
    private String role;
}
