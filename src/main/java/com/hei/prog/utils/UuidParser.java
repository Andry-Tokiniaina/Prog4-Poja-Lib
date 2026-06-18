package com.hei.prog.utils;

import com.hei.prog.endpoint.exception.InvalidUuidException;
import java.util.UUID;

public class UuidParser {
  public static UUID parse(String value) {
    try {
      return UUID.fromString(value);
    } catch (IllegalArgumentException e) {
      throw new InvalidUuidException(value);
    }
  }
}
