package com.hei.prog.endpoint.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionBody> handleNotFoundException(
      NotFoundException exception, HttpServletRequest request) {
    return ResponseEntity.status(404)
        .body(
            new ExceptionBody(
                404,
                "Resource not found",
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now()));
  }

  @ExceptionHandler({
    MethodArgumentNotValidException.class,
    MethodArgumentTypeMismatchException.class
  })
  public ResponseEntity<ExceptionBody> handleMethodArgumentTypeMismatchOrNotValidException(
      Exception exception, HttpServletRequest request) {
    return ResponseEntity.badRequest()
        .body(
            new ExceptionBody(
                400, "Bad request", exception.getMessage(), request.getPathInfo(), Instant.now()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionBody> handleIllegalArgumentException(
      IllegalArgumentException exception, HttpServletRequest request) {
    return ResponseEntity.badRequest()
        .body(
            new ExceptionBody(
                400,
                "Illegal argument",
                exception.getMessage(),
                request.getPathInfo(),
                Instant.now()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionBody> handleException(
      Exception exception, HttpServletRequest request) {
    return ResponseEntity.internalServerError()
        .body(
            new ExceptionBody(
                500,
                "An internal error has occurred",
                exception.getMessage(),
                request.getPathInfo(),
                Instant.now()));
  }

  @ExceptionHandler(InvalidUuidException.class)
  public ResponseEntity<ExceptionBody> handleInvalidUuidException(
      InvalidUuidException exception, HttpServletRequest request) {
    return ResponseEntity.badRequest()
        .body(
            new ExceptionBody(
                400,
                "Invalid UUID format",
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now()));
  }
}
