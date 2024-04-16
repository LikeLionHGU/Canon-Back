package org.example.canon.exception;

public class LikeDeleteDisableException extends RuntimeException {
  public LikeDeleteDisableException() {

    super("좋아요를 한 유저가 아니므로 삭제할 수 없습니다.");
  }
}
