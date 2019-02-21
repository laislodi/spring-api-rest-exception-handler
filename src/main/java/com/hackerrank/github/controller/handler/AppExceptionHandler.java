package com.hackerrank.github.controller.handler;


import com.hackerrank.github.controller.domain.MyError;
import com.hackerrank.github.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ExistentActorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyError catchBadRequest(ExistentActorException e) {
        return new MyError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ExistentEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyError catchKnownServerError(ExistentEventException e) {
        return new MyError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(BadActorUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyError catchKnownServerError(BadActorUpdateException e) {
        return new MyError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyError catchNotFound(EventNotFoundException e) {
        return new MyError(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(ActorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyError catchNotFound(ActorNotFoundException e) {
        return new MyError(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
