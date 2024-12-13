package com.github.luisfelipetochamartins.medical.clini.usuario;

import com.github.luisfelipetochamartins.medical.clini.infra.security.TokenJWTRecord;
import com.github.luisfelipetochamartins.medical.clini.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    private final UsuarioService service;
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @Autowired
    public RegisterController(UsuarioService service, AuthenticationManager manager, TokenService tokenService) {
        this.service = service;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJWTRecord> register(@RequestBody @Valid AuthRecord record) {
        var user = service.insertIfNotExists(record);
        var authenticationToken = new UsernamePasswordAuthenticationToken(record.usuario(), record.senha());
        var authentication = manager.authenticate(authenticationToken);

        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTRecord(token));
    }
}
