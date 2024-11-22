package senai.agencia.usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senai.agencia.dto.DestinosDTO;
import senai.agencia.entidade.Destinos;

@Service
@RequiredArgsConstructor
public class UsuarioServico implements UserDetailsService {

    private final UsuarioRepositorio repositorio;

    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repositorio.findByLogin(login);
    }

    //Cadastra um novo usuário no banco
    public DadosUsuarioCadastro criarUsuario(DadosUsuarioCadastro dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        repositorio.save(usuario);
        return modelMapper.map(usuario, DadosUsuarioCadastro.class);
    }
}
