package br.com.vita.projeto.base.model.exceptions;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.vita.projeto.base.model.dto.MensagemRetorno;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(400).body(errors);
    }
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<MensagemRetorno> handleInvalidFormatException(InvalidFormatException e){
        return ResponseEntity.status(400).body(new MensagemRetorno(e.getLocalizedMessage()));
    }
}
