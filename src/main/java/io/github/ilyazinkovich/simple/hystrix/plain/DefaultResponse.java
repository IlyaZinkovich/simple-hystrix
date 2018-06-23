package io.github.ilyazinkovich.simple.hystrix.plain;

public class DefaultResponse implements Response {

  @Override
  public boolean isOK() {
    return true;
  }
}
