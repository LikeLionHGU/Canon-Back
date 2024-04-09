package org.example.canon.exception;

public class PostNotFoundException extends RuntimeException {

  private static final String POST_NOT_FOUND = "일치하는 글을 찾을 수 없습니다.";

  public PostNotFoundException() {
    super(POST_NOT_FOUND);
  }
}
