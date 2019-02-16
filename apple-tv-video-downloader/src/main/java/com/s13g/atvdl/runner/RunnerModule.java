package com.s13g.atvdl.runner;

import dagger.Module;
import dagger.Provides;

@Module
public class RunnerModule {

  @Provides
  static Runner mainRunner(RunnerImpl runner) {
    return runner;
  }
}
