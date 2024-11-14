package com.JWT.JWT.Authnetication.Exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger  = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Exceptionhandler> UserNotFound(UserNotFoundException ex){
        logger.info("User not found {}",ex);
        Exceptionhandler user = Exceptionhandler.builder().message("User is not found").success(false).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
    }

}
