package org.example.canon.exception;

public class UserAlreadyLikedException extends RuntimeException {
    public UserAlreadyLikedException() {
        super("해당 게시물에 이미 좋아요를 눌렀습니다.");
    }
}
