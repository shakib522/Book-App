package com.example.bookapp.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus
@ControllerAdvice
public class RestErrorResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorMessage> defaultException(DefaultException exception) {
        ErrorMessage message = new ErrorMessage(
                "Failed",
                exception.getMessage(),
                exception.getStatusCode()
        );
        return ResponseEntity.status(exception.getStatusCode()).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request
    ) {
        ErrorMessage message = new ErrorMessage(
                "Failed",
                ex.getMessage(),
                statusCode.value()
        );
        return ResponseEntity.status(statusCode.value()).body(message);
    }
}
