package com.davicarv.choperia.controllera.apirest;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.davicarv.choperia.exception.PropertyError;
import com.davicarv.choperia.exception.ValidationError;

@RestControllerAdvice
public class MyRestControllerAdvice {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity erroValidacao(ConstraintViolationException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(
				Calendar.getInstance(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(),  
				HttpStatus.UNPROCESSABLE_ENTITY.name(), 
				e.getMessage(), 
				request.getRequestURI());
		
		for(ConstraintViolation cv: e.getConstraintViolations()) {
			PropertyError p = new PropertyError(cv.getPropertyPath().toString(), cv.getMessage());
			error.getErrors().add(p);
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity erroValidacao(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(
				Calendar.getInstance(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(),  
				HttpStatus.UNPROCESSABLE_ENTITY.name(), 
				e.getMessage(), 
				request.getRequestURI());
		
		for(FieldError fe: e.getBindingResult().getFieldErrors()) {
			PropertyError p = new PropertyError(fe.getField(), fe.getDefaultMessage());
			error.getErrors().add(p);
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity erroPadrao(Exception e, HttpServletRequest request) {
		ValidationError error = new ValidationError(
				Calendar.getInstance(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(),  
				HttpStatus.UNPROCESSABLE_ENTITY.name(), 
				e.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
}
