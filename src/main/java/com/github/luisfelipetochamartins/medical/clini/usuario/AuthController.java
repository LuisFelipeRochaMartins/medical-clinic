package com.github.luisfelipetochamartins.medical.clini.usuario;

import com.github.luisfelipetochamartins.medical.clini.infra.security.TokenJWTRecord;
import com.github.luisfelipetochamartins.medical.clini.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class AuthController {

	private final AuthenticationManager manager;
	private final TokenService tokenService;

	@Autowired
	public AuthController(AuthenticationManager manager, TokenService tokenService) {
		this.manager = manager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity login(@RequestBody @Valid AuthRecord auth) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(auth.usuario(), auth.senha());
		var authentication = manager.authenticate(authenticationToken);

		var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());

		return ResponseEntity.ok(new TokenJWTRecord(tokenJWT));
	}
}
