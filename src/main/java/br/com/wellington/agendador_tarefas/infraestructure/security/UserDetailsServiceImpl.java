package br.com.wellington.agendador_tarefas.infraestructure.security;


import br.com.wellington.agendador_tarefas.business.dto.UsuarioDto;
import br.com.wellington.agendador_tarefas.infraestructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {
    @Autowired
    private UsuarioClient usuarioClient;


    public UserDetails carregaDadosUsuarios(String email, String token) {
        UsuarioDto usuarioDto = usuarioClient.buscaUsuarioPorEmail(email, token);
            return User
                    .withUsername(usuarioDto.getEmail()) // Define o nome de usuário como o e-mail
                    .password(usuarioDto.getSenha()) // Define a senha do usuário
                    .build();

    }
}
