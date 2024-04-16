package org.example.canon.exception;

public class CommentDeleteDisableException extends RuntimeException {
  public CommentDeleteDisableException() {

    super("댓글 작성자가 아니므로 삭제할 수 없습니다.");
  }
}
