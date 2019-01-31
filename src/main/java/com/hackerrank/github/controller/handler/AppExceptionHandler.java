package com.hackerrank.github.controller.handler;

import com.hackerrank.github.exception.BadRequestException;
import com.hackerrank.github.exception.NoActorEventsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NoActorEventsFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void catchBadRequest(BadRequestException e) {
        return;
    }

}
