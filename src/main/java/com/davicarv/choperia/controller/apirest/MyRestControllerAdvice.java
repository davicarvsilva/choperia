package com.davicarv.choperia.controller.apirest;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.davicarv.choperia.exception.PropertyError;
import com.davicarv.choperia.exception.ValidationError;
import com.davicarv.choperia.exception.Error;
import com.davicarv.choperia.exception.NotFoundException;

//Classe de escuta das excessões geradas pelos Controllers
@RestControllerAdvice
public class MyRestControllerAdvice {

	@ExceptionHandler(ConstraintViolationException.class)	
	public ResponseEntity erroValidacao(
			ConstraintViolationException e, 
			HttpServletRequest request) {
		
		ValidationError error = new ValidationError(
				Calendar.getInstance(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), 
				"Erro de validação", 
				request.getRequestURI());

		for (ConstraintViolation cv : e.getConstraintViolations()) {
			PropertyError p = new PropertyError(
					cv.getPropertyPath().toString(), 
					cv.getMessage());
			
			error.getErrors().add(p);
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity erroValidacao(
			MethodArgumentNotValidException e, 
			HttpServletRequest request) {
		
		ValidationError error = new ValidationError(
				Calendar.getInstance(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), 
				e.getMessage(), 
				request.getRequestURI());

		for (FieldError fe : e.getBindingResult().getFieldErrors()) {
			PropertyError p = new PropertyError(fe.getField(), fe.getDefaultMessage());
			error.getErrors().add(p);
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity erroPadrao(NotFoundException e, HttpServletRequest request) {
		
		Error error = new Error(
			Calendar.getInstance(), 
			HttpStatus.NOT_FOUND.value(), 
			HttpStatus.NOT_FOUND.name(),
			e.getMessage(),
			request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity erroPadrao(Exception e, HttpServletRequest request) {
		
		Error error = new Error(
			Calendar.getInstance(), 
			HttpStatus.BAD_REQUEST.value(), 
			HttpStatus.BAD_REQUEST.name(),
			e.getMessage(),
			request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
