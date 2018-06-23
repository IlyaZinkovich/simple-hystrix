package io.github.ilyazinkovich.simple.hystrix.plain;

import java.util.function.Function;
import java.util.function.Supplier;

class ClientApp {

  private final WebService webService;

  ClientApp(final WebService webService) {
    this.webService = webService;
  }

  Response callWebService() {
    final String groupKey = "client-app";
    final String commandKey = "call-web-service";
    final Supplier<Response> command = webService::call;
    final Function<Throwable, Response> fallback = this::defaultResponse;
    return new CommandWithFallback<>(command, fallback, groupKey, commandKey)
        .execute();
  }

  private Response defaultResponse(final Throwable error) {
    error.printStackTrace();
    return new DefaultResponse();
  }
}
