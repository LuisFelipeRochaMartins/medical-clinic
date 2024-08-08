package com.github.luisfelipetochamartins.medical.clini.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity notFound() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity badRequest(MethodArgumentNotValidException err) {
		var erros = err.getFieldErrors();

		return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacao::new).collect(Collectors.toList()));
	}

	private record ErroValidacao(String campo, String mensagem) {
		public ErroValidacao(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}
