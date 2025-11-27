package org.poligran.arqweb.crudproductos.infrastructure.rest;

import org.poligran.arqweb.crudproductos.application.ProductNotFoundException;
import com.mongodb.MongoException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(WebExchangeBindException ex, ServerWebExchange exchange) {
        Map<String, Object> body = baseBody(HttpStatus.BAD_REQUEST, "Error de validación", exchange);
        Map<String, String> fieldErrors = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage() != null ? err.getDefaultMessage() : "Valor inválido",
                        (a, b) -> a
                ));
        body.put("errors", fieldErrors);
        return body;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(ProductNotFoundException ex, ServerWebExchange exchange) {
        Map<String, Object> body = baseBody(HttpStatus.NOT_FOUND, "Recurso no encontrado", exchange);
        body.put("message", ex.getMessage());
        return body;
    }

    @ExceptionHandler({DataAccessException.class, MongoException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String, Object> handleDatabase(Exception ex, ServerWebExchange exchange) {
        Map<String, Object> body = baseBody(HttpStatus.SERVICE_UNAVAILABLE, "Error de conexión a la base de datos", exchange);
        body.put("message", ex.getMessage());
        return body;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneric(Throwable ex, ServerWebExchange exchange) {
        Map<String, Object> body = baseBody(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", exchange);
        body.put("message", ex.getMessage());
        return body;
    }

    private Map<String, Object> baseBody(HttpStatus status, String error, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", error);
        body.put("path", exchange.getRequest().getPath().value());
        return body;
    }
}
