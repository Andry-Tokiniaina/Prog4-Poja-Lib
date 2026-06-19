package com.hei.prog.endpoint.exception;

public class InvalidUuidException extends RuntimeException {
  public InvalidUuidException(String value) {
    super("Invalid UUID format: '" + value + "'");
  }
}
