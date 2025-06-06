package com.pm.patientService.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        log.warn("Email address already exists {}", ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message","Email address already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlerPatientNotFoundException(PatientNotFoundException ex){
        log.warn("Patient not found {}",ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message","Patient not Found");
        return ResponseEntity.badRequest().body(errors);
    }

}
