package com.github.luisfelipetochamartins.medical.clini.usuario;

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

	private AuthenticationManager manager;

	@Autowired
	public AuthController(AuthenticationManager manager) {
		this.manager = manager;
	}

	@PostMapping
	public ResponseEntity login(@RequestBody @Valid AuthRecord auth) {
		var token = new UsernamePasswordAuthenticationToken(auth.usuario(), auth.senha());
		var authentication = manager.authenticate(token);

		return ResponseEntity.ok().build();
	}
}
