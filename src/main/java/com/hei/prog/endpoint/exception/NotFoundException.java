package com.hei.prog.endpoint.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String resource, Object id) {
    super(resource + " not found: " + id);
  }
}
