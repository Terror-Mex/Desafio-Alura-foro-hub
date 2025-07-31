package com.adrian.forohub.infra.errores;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>>tratarError404(EntityNotFoundException ex){
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErroresDeValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }
    public ResponseEntity<Map<String, String>> tratarConstraintViolations(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getConstraintViolations().forEach(cv ->
                errores.put(cv.getPropertyPath().toString(), cv.getMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }
}
