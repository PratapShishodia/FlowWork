package com.ps.flowwork_backend.handler;

import com.ps.flowwork_backend.common.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleException(HandlerMethodValidationException exception) {
        Map<String, String> errors = new HashMap<>();
        List<ParameterValidationResult> results = exception.getParameterValidationResults();
        results.forEach(result -> {
            String paramName = result.getMethodParameter().getParameterName();

            // Combine all messages into a single comma-separated string
            String combinedMessages = result.getResolvableErrors()
                    .stream()
                    .map(MessageSourceResolvable::getDefaultMessage)  // extract each message
                    .collect(Collectors.joining(", "));       // join messages
            errors.put(paramName, combinedMessages);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullException(Exception exception, WebRequest webRequest) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                "A NullPointerException occurred due to : " + exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, Object>> handleTransactionException(
            TransactionSystemException ex) {

        Throwable cause = ex;

        while (cause != null) {

            if (cause instanceof ConstraintViolationException validationException) {

                List<String> errors = validationException
                        .getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .toList();

                return ResponseEntity.badRequest().body(
                        Map.of(
                                "success", false,
                                "message", "Validation failed",
                                "errors", errors
                        )
                );
            }

            cause = cause.getCause();
        }

        assert ex != null;
        return ResponseEntity.internalServerError().body(
                Map.of(
                        "success", false,
                        "message", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonException(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.badRequest().body(
                Map.of(
                        "success", false,
                        "message", "Invalid request body",
                        "errors", List.of(ex.getMostSpecificCause().getMessage())
                )
        );
    }
}
