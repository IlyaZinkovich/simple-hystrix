package io.github.ilyazinkovich.simple.hystrix.annotation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

class ClientApp {

  private final WebService webService;

  ClientApp(final WebService webService) {
    this.webService = webService;
  }

  @HystrixCommand(groupKey = "client-app", commandKey = "call-web-service",
      fallbackMethod = "defaultResponse")
  Response callWebService() {
    return webService.call();
  }

  private Response defaultResponse(final Throwable error) {
    error.printStackTrace();
    return () -> true;
  }
}
