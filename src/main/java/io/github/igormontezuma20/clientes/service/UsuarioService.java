package io.github.igormontezuma20.clientes.service;

import io.github.igormontezuma20.clientes.exception.UsuarioCadastradoException;
import io.github.igormontezuma20.clientes.model.entity.Usuario;
import io.github.igormontezuma20.clientes.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario saveUser(Usuario usuario) {
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if (exists ) {
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
