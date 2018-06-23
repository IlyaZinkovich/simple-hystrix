package io.github.ilyazinkovich.simple.hystrix.annotation;

public class DefaultResponse implements Response {

  @Override
  public boolean isOK() {
    return true;
  }
}
