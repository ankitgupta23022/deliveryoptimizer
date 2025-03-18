package com.lucidity.deliveryoptimizer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleBadRequest(IllegalArgumentException ex) {
        logger.error("Bad Request: {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleGenericException(Exception ex) {
        logger.error("Internal Server Error", ex);
        Map<String, String> response = new HashMap<>();
        response.put("error", "Internal Server Error");
        return response;
    }

}