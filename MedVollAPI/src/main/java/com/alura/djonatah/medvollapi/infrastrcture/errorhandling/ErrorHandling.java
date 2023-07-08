package com.alura.djonatah.medvollapi.infrastrcture.errorhandling;

import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandling404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandling400(MethodArgumentNotValidException exception){
        var listOfErrors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(listOfErrors.stream().map( t-> new FieldError400(t)).toList());
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity errorHandlingDataValidation(DataValidationException exception){
        var errorMessage = exception.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    private record FieldError400(String field, String errorMessage){
        public FieldError400(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
