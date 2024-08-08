package com.github.luisfelipetochamartins.medical.clini.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

	private final UsuarioRepository repository;

	@Autowired
	public AuthService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		return (UserDetails) repository.findByUsuario(usuario);
	}
}
