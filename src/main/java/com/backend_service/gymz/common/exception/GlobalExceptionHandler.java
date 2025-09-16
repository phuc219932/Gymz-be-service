package com.backend_service.gymz.common.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.web.bind.MissingServletRequestParameterException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ErrorResponse.FieldError> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .rejectedValue(error.getRejectedValue())
                        .build())
                .collect(Collectors.toList());

        log.warn("Validation failed for request: {} - Errors: {}", request.getRequestURI(), fieldErrors);

        return ErrorResponse.builder()
                .message("Invalid request data")
                .errorCode("VALIDATION_ERROR")
                .fieldErrors(fieldErrors)
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    @ExceptionHandler({
        MethodArgumentTypeMismatchException.class,
        IllegalArgumentException.class,
        PropertyReferenceException.class,
        HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestExceptions(Exception ex, HttpServletRequest request) {
        String errorCode = "BAD_REQUEST";
        String message = ex.getMessage();

        if (ex instanceof MethodArgumentTypeMismatchException mismatchEx) {
            errorCode = "ARGUMENT_TYPE_MISMATCH";
            message = String.format("Parameter '%s' value '%s' type mismatch. Required: %s",
                    mismatchEx.getName(), mismatchEx.getValue(),
                    mismatchEx.getRequiredType() != null ? mismatchEx.getRequiredType().getSimpleName() : "unknown");
            log.warn("MethodArgumentTypeMismatchException: {}", message);
        } else if (ex instanceof IllegalArgumentException) {
            errorCode = "ILLEGAL_ARGUMENT";
            message = "Invalid argument: " + ex.getMessage();
            log.warn("IllegalArgumentException: {}", ex.getMessage());
        } else if (ex instanceof PropertyReferenceException) {
            errorCode = "INVALID_SORT_PROPERTY";
            message = "Invalid sort property: " + ex.getMessage();
            log.warn("PropertyReferenceException: {}", ex.getMessage());
        } else if (ex instanceof HttpMessageNotReadableException notReadableEx) {
            errorCode = "MALFORMED_JSON";
            String detail = notReadableEx.getMostSpecificCause() != null
                    ? notReadableEx.getMostSpecificCause().getMessage()
                    : notReadableEx.getMessage();
            message = "Malformed or unreadable request body";
            if (detail != null && !detail.equals(message)) {
                message += ": " + detail;
            }
            log.warn("Malformed JSON request: {}", detail);
        }

        return ErrorResponse.builder()
                .message(message)
                .errorCode(errorCode)
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        String message = String.format("Method %s not supported. Supported: %s", ex.getMethod(), ex.getSupportedHttpMethods());

        return ErrorResponse.builder()
                .message(message)
                .errorCode("METHOD_NOT_ALLOWED")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        log.warn("Authentication failed: {}", ex.getMessage());
        return ErrorResponse.builder()
                .message("Authentication failed or credentials invalid")
                .errorCode("AUTHENTICATION_FAILED")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.warn("Access denied: {}", ex.getMessage());
        return ErrorResponse.builder()
                .message("Access denied")
                .errorCode("ACCESS_DENIED")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    // Handler for missing required request parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String message = String.format("Missing required parameter: %s", ex.getParameterName());
        log.warn("MissingServletRequestParameterException: {}", message);

        return ErrorResponse.builder()
                .message(message)
                .errorCode("MISSING_PARAMETER")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    // Handler for constraint violations (e.g. @RequestParam, @PathVariable validation)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        String message = "Constraint violation: " + ex.getMessage();
        log.warn("ConstraintViolationException: {}", message);

        return ErrorResponse.builder()
                .message(message)
                .errorCode("CONSTRAINT_VIOLATION")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    // Handler for custom InvalidDataException
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException ex, HttpServletRequest request) {
        log.warn("InvalidFormatException: {}", ex.getMessage());

        String fieldName = ex.getPath().isEmpty() ? "unknown" : ex.getPath().get(0).getFieldName();
        String targetType = ex.getTargetType().getSimpleName();
        String value = ex.getValue() != null ? ex.getValue().toString() : "null";
        String message = String.format("Invalid value '%s' for field '%s'. Expected type: %s. Accepted values: %s",
                value, fieldName, targetType,
                ex.getTargetType().isEnum() ? java.util.Arrays.toString(ex.getTargetType().getEnumConstants())
                        : targetType);

        return ErrorResponse.builder()
                .message(message)
                .errorCode("INVALID_FORMAT")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidDataException(InvalidDataException ex, HttpServletRequest request) {
        log.warn("InvalidDataException: {}", ex.getMessage());
        return ErrorResponse.builder()
                .message("Invalid data: " + ex.getMessage())
                .errorCode("INVALID_DATA")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .traceId(getTraceId())
                .build();
    }

    private String getTraceId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private String getCurrentUsername() {
        // Get from SecurityContext if needed
        return "anonymous"; // placeholder
    }
}
