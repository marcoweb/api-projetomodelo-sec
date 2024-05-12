package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import application.model.Usuario;
import application.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepo.findByNomeDeUsuario(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuário Não Encontrado");
        }
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getNomeDeUsuario())
                        .password(user.getSenha())
                        .roles("USER")
                        .build();
    
        return userDetails;
    }
}
