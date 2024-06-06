package org.example.canon.exception;

public class DoNotLoginException extends RuntimeException{
    private static final String MESSAGE = "로그인이 되어있지 않습니다..";



    public DoNotLoginException(String message) {
        super(message);
    }
}
