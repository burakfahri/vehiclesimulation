package com.tba.exception;

public class EmptyInstanceException extends Exception {
    public EmptyInstanceException() { super(); }
    public EmptyInstanceException(String message) { super(message); }
    public EmptyInstanceException(String message, Throwable cause) { super(message, cause); }
    public EmptyInstanceException(Throwable cause) { super(cause); }
}
