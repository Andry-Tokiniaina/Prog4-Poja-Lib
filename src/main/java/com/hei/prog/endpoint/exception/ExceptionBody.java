package com.hei.prog.endpoint.exception;

import java.time.Instant;

public record ExceptionBody(
    int status, String message, String details, String path, Instant timestamp) {}
