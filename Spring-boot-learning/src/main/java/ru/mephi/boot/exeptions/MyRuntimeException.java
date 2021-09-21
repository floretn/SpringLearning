package ru.mephi.boot.exeptions;

public class MyRuntimeException extends RuntimeException{
    public MyRuntimeException(String message) {
        super(message);
    }
}
