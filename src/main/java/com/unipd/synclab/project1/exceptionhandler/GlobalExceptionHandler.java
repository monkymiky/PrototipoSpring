package com.unipd.synclab.project1.exceptionhandler ;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;
import com.unipd.synclab.project1.exception.ReferencedResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", status.value());
        body.put("error", "Validation Failed");

        // Raccogli tutti gli errori di validazione
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Invalid value"
                ));
        body.put("errors", fieldErrors);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, headers, status); // HttpStatus.BAD_REQUEST
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.warn("Resource not found: {}", ex.getMessage());
        return buildErrorResponse(ex, "Resource not found", HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ReferencedResourceNotFoundException.class)
    public ResponseEntity<Object> handleReferencedResourceNotFoundException(ReferencedResourceNotFoundException ex, WebRequest request) {
        log.warn("Referenced resource not found: {}", ex.getMessage());
        return buildErrorResponse(ex, "Invalid reference in request payload", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex); // Logga lo stack trace per errori imprevisti
        return buildErrorResponse(ex, "An unexpected internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    // Metodo helper per costruire la risposta di errore
    private ResponseEntity<Object> buildErrorResponse(Exception ex, String message, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase()); // Es. "Not Found", "Bad Request"
        body.put("message", message + (ex.getMessage() != null ? ": " + ex.getMessage() : ""));
        body.put("path", request.getDescription(false).replace("uri=", ""));
        // In produzione, potresti non voler esporre ex.getMessage() direttamente se contiene dettagli sensibili.
        return new ResponseEntity<>(body, status);
    }
}