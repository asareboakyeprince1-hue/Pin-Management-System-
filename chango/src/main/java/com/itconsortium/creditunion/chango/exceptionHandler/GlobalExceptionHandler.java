package com.itconsortium.creditunion.chango.exceptionHandler;

import com.itconsortium.creditunion.chango.exception.PinException;
import com.itconsortium.creditunion.chango.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PinException.class)
    public ResponseEntity<String> handlePinException(PinException e){
        log.error(e.getMessage());
        return ResponseEntity.status(500).body("Problem in the server");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e){
        return ResponseEntity.status(404).body("The user is not found");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> gandleGenericException(Exception e){
        log.error(e.getMessage());
        return ResponseEntity.status(500).body("There is a problem in the server");
    }
}
