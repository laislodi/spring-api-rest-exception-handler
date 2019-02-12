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
        //LOGGER.warn(e.getMessage());
        return new MyError(e.getMessage(), //Level.WARN.toString(),
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ExistentEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyError catchKnownServerError(ExistentEventException e) {
        //LOGGER.error(e.getMessage(), e);
        return new MyError(e.getMessage(), //Level.ERROR.toString(),
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(BadActorUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyError catchKnownServerError(BadActorUpdateException e) {
        //LOGGER.error(e.getMessage(), e);
        return new MyError(e.getMessage(), //Level.ERROR.toString(),
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyError catchNotFound(EventNotFoundException e) {
        //LOGGER.warn(e.getMessage());
        return new MyError(e.getMessage(), //Level.WARN.toString(),
                HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(ActorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyError catchNotFound(ActorNotFoundException e) {
        //LOGGER.warn(e.getMessage());
        return new MyError(e.getMessage(), //Level.WARN.toString(),
                HttpStatus.NOT_FOUND.value());
    }
    //

}
