package com.s13g.atvdl.runner;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RunnerModule_MainRunnerFactory implements Factory<Runner> {
  private final Provider<RunnerImpl> runnerProvider;

  public RunnerModule_MainRunnerFactory(Provider<RunnerImpl> runnerProvider) {
    this.runnerProvider = runnerProvider;
  }

  @Override
  public Runner get() {
    return proxyMainRunner(runnerProvider.get());
  }

  public static RunnerModule_MainRunnerFactory create(Provider<RunnerImpl> runnerProvider) {
    return new RunnerModule_MainRunnerFactory(runnerProvider);
  }

  public static Runner proxyMainRunner(RunnerImpl runner) {
    return Preconditions.checkNotNull(
        RunnerModule.mainRunner(runner),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
