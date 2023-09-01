package io.github.igormontezuma20.clientes.rest;

import io.github.igormontezuma20.clientes.exception.UsuarioCadastradoException;
import io.github.igormontezuma20.clientes.model.entity.Usuario;
import io.github.igormontezuma20.clientes.model.repository.UsuarioRepository;
import io.github.igormontezuma20.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid Usuario user){
        try {
        service.saveUser(user);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
