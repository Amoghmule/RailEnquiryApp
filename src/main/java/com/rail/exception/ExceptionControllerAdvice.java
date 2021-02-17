package com.rail.exception;


import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.rail.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger logger= LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex){
		ErrorMessage error=new ErrorMessage();
		
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		
		logger.error(" Error : "+ex.getMessage());
		
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(BusinessException ex){
		
		ErrorMessage error=new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getMessage());
	     
	     logger.error(" Business Exception : "+ex.getMessage());
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex){
		
		ErrorMessage error=new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     
	     String message=ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
	     error.setMessage(message);
		
	     logger.error(" Validation Exception : "+message);
		
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex){
		ErrorMessage error=new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     
	     String message=ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
	     error.setMessage(message);
		
	     logger.error(" Constraint Violation Exception : "+message);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
}
