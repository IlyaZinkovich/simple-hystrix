package io.github.ilyazinkovich.simple.hystrix.plain;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

class HystrixTest {

  @Test
  void testReliable() {
    final Random alwaysTrue = new SameBoolean(TRUE);
    final WebService webService = new WebService(alwaysTrue);
    final ClientApp reliableApp = new ClientApp(webService);

    final Response response = reliableApp.callWebService();

    assertTrue(response.isOK());
  }

  @Test
  void testUnreliable() {
    final Random alwaysFalse = new SameBoolean(FALSE);
    final WebService webService = new WebService(alwaysFalse);
    final ClientApp unreliableApp = new ClientApp(webService);

    final Response response = unreliableApp.callWebService();

    assertTrue(response.isOK());
  }

  private static final class SameBoolean extends Random {

    private final boolean value;

    SameBoolean(final boolean value) {
      this.value = value;
    }

    @Override
    public boolean nextBoolean() {
      return value;
    }
  }
}
