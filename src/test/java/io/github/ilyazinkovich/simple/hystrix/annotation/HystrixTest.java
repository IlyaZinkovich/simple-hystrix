package io.github.ilyazinkovich.simple.hystrix.annotation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class HystrixTest {

  @Test
  void testReliable() {
    final ApplicationContext context =
        new AnnotationConfigApplicationContext(Context.class);
    final ClientApp reliableApp =
        context.getBean("reliableApp", ClientApp.class);

    final Response response = reliableApp.callWebService();

    assertTrue(response.isOK());
  }

  @Test
  void testUnreliable() {
    final ApplicationContext context =
        new AnnotationConfigApplicationContext(Context.class);
    final ClientApp unreliableApp =
        context.getBean("unreliableApp", ClientApp.class);

    final Response response = unreliableApp.callWebService();

    assertTrue(response.isOK());
  }
}
