package com.api.annualreportmgmt.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends AbstractExceptionResponse {

    // 🔒 Handles invalid credentials (401 Unauthorized)
    @ExceptionHandler({ BadCredentialsException.class, AuthenticationException.class })
    public ResponseEntity<Object> handleAuthenticationException(Exception ex, HttpServletRequest request) {
        return  super.setAuthentication(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    // 🚫 Handles forbidden access (403 Forbidden)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return super.setAuthentication(HttpStatus.FORBIDDEN, "You do not have permission to access this resource");
    }

    // ⚠️ Catch-all fallback (optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest request) {
        return super.setAuthentication(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
