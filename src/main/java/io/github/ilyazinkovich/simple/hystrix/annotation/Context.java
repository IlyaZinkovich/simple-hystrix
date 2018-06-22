package io.github.ilyazinkovich.simple.hystrix.annotation;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
class Context {

  @Bean
  HystrixCommandAspect hystrixAspect() {
    return new HystrixCommandAspect();
  }

  @Bean
  ClientApp reliableApp() {
    final Random alwaysTrue = new SameBoolean(TRUE);
    final WebService webService = new WebService(alwaysTrue);
    return new ClientApp(webService);
  }

  @Bean
  ClientApp unreliableApp() {
    final Random alwaysFalse = new SameBoolean(FALSE);
    final WebService webService = new WebService(alwaysFalse);
    return new ClientApp(webService);
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
