package io.github.ilyazinkovich.simple.hystrix.plain;

import java.util.Random;

class WebService {

  private final Random random;

  WebService(final Random random) {
    this.random = random;
  }

  Response call() {
    if (random.nextBoolean()) {
      return () -> true;
    } else {
      throw new RuntimeException("Something went wrong.");
    }
  }
}
