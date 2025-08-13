package br.com.lucasmafra.tarefas.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonParseError(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();

        if (ex.getCause() instanceof InvalidFormatException invalidFormatEx) {
            String fieldName = invalidFormatEx.getPath().get(0).getFieldName();
            String targetType = invalidFormatEx.getTargetType().getSimpleName();
            errors.put(fieldName, "Formato inválido para " + targetType + ". Corrija o valor.");
        } else {
            errors.put("error", "JSON inválido ou campos incorretos: " + ex.getMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }
}
