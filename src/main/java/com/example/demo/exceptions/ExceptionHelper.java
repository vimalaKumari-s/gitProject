package com.example.demo.exceptions;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);
    @ExceptionHandler(value = { InvalidRemoteException.class })
    ResponseEntity<Object> handleInvalidRemoteException(InvalidRemoteException ex){
        logger.error("Issue with the remote provided ",ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = { TransportException.class })
//    ResponseEntity<Object> handleTransportException(TransportException ex){
//        logger.error("Some issue happened in the transport",ex.getMessage());
//        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(value = { GitAPIException.class })
    ResponseEntity<Object> handleGitApiException(GitAPIException ex){
        logger.error("Exception occurred while working in the git",ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
