package com.github.luisfelipetochamartins.medical.clini.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Usuario insertIfNotExists(AuthRecord record) {
        var user = repository.findByUsuario(record.usuario());

        if (user != null) {
            return user;
        }
        user = new Usuario(null, record.usuario(), encoder.encode(record.senha()));
        return repository.save(user);
    }
}
