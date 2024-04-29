package org.example.canon.exception;

public class PostEditDisableException extends RuntimeException {
    public PostEditDisableException() {
        super("작성자가 아니면 수정할 수 없습니다.");
    }
}
