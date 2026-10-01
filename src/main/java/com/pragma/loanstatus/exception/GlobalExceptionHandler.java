package com.pragma.loanstatus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidStateTransitionException.class)
    public ResponseEntity<Object> handleInvalidStateTransition(
            InvalidStateTransitionException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Invalid State Transition");
        body.put("message", ex.getMessage());
        
        Map<String, Object> details = new HashMap<>();
        if (ex.getCurrentStatus() != null) {
            details.put("currentStatus", ex.getCurrentStatus().name());
        }
        if (ex.getRequestedStatus() != null) {
            details.put("requestedStatus", ex.getRequestedStatus().name());
        }
        if (ex.getApplicantId() != null) {
            details.put("applicantId", ex.getApplicantId());
        }
        details.put("allowedTransitions", ex.getDetailedMessage());
        body.put("details", details);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StateConsistencyException.class)
    public ResponseEntity<Object> handleStateConsistencyException(
            StateConsistencyException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "State Consistency Error");
        body.put("message", ex.getMessage());
        
        Map<String, Object> details = new HashMap<>();
        details.put("loanApplicationId", ex.getLoanApplicationId());
        details.put("originSystem", ex.getOriginSystem());
        details.put("targetSystem", ex.getTargetSystem());
        details.put("detectedAt", ex.getDetectedAt().toString());
        details.put("inconsistencyDetails", ex.getInconsistencyDetails());
        details.put("latencyViolation", ex.isLatencyViolation());
        body.put("details", details);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        HttpStatus status = ex.isLatencyViolation() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.CONFLICT;
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(
            NullPointerException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Se produjo un error interno: " + ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(
            Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Error inesperado: " + ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}