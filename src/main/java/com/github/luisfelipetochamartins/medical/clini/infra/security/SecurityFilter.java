package com.github.luisfelipetochamartins.medical.clini.infra.security;

import com.github.luisfelipetochamartins.medical.clini.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UsuarioRepository repository;

	@Autowired
	public SecurityFilter(TokenService service, UsuarioRepository repository) {
		this.tokenService = service;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var tokenJWT = getTokenFromRequest(request);
		if (tokenJWT != null) {
			var subject = tokenService.getSubject(tokenJWT);
			var usuario = repository.findByUsuario(subject);

			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		var authorization = request.getHeader("Authorization");
		if (authorization != null) {
			return authorization.replace("Bearer ", "");
		}

		return null;
	}
}
