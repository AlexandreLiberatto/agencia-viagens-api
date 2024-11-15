package senai.agencia.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Informa ao Spring que está classe é de configuração
public class Configuracoes {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
