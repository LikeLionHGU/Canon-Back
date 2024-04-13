package org.example.canon.exception;

public class PostDeleteDisableException extends RuntimeException {
  public PostDeleteDisableException() {
    super("게시글 작성자가 아니므로 삭제할 수 없습니다.");
  }
}
