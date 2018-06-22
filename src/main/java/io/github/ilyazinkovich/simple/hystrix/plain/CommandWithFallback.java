package io.github.ilyazinkovich.simple.hystrix.plain;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import java.util.function.Function;
import java.util.function.Supplier;

class CommandWithFallback<T> extends HystrixCommand<T> {

  private final Supplier<T> command;
  private final Function<Throwable, T> fallback;

  CommandWithFallback(
      final Supplier<T> command, final Function<Throwable, T> fallback,
      final String groupKey, final String commandKey) {
    super(Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
        .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
    );
    this.command = command;
    this.fallback = fallback;
  }

  @Override
  protected T run() {
    return command.get();
  }

  @Override
  protected T getFallback() {
    return fallback.apply(getExecutionException());
  }
}
