package senai.agencia.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity //declara que é uma indentidade
@Table(name = "usuario") //declara que é uma tabela
@Data // gera getters e setters
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id //mostra pro spring que isso é um id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera automaticamente o id
    private Long id;
    private String login;
    private  String password;

    @Column(nullable = false)
    private String role; // "ROLE_USER" ou "ROLE_ADMIN"

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
