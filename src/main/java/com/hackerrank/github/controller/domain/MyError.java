package com.hackerrank.github.controller.domain;

public class MyError {


    private final String message;
    private final int value;

    public MyError(String message, int value) {
        this.message = message;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }
}
