package com.s13g.atvdl;

import com.s13g.atvdl.data.DataModule;
import com.s13g.atvdl.download.DownloadModule;
import com.s13g.atvdl.runner.Runner;
import com.s13g.atvdl.runner.RunnerModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * The main Dagger component of this app.
 */
@Singleton
@Component(modules = {DownloadModule.class, RunnerModule.class, DataModule.class})
public interface MainComponent {
  Runner mainAppRunner();
}
