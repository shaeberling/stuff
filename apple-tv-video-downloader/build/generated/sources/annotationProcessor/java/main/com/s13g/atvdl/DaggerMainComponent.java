package com.s13g.atvdl;

import com.s13g.atvdl.data.DataModule;
import com.s13g.atvdl.data.DataModule_MVideoInfoParserFactory;
import com.s13g.atvdl.data.VideoInfoParser;
import com.s13g.atvdl.data.VideoInfoParserImpl;
import com.s13g.atvdl.download.DownloadModule;
import com.s13g.atvdl.download.DownloadModule_ProvideHeaterFactory;
import com.s13g.atvdl.download.DownloadModule_UrlToStringLoaderFactory;
import com.s13g.atvdl.runner.Runner;
import com.s13g.atvdl.runner.RunnerImpl;
import com.s13g.atvdl.runner.RunnerModule;
import com.s13g.atvdl.runner.RunnerModule_MainRunnerFactory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerMainComponent implements MainComponent {
  private DaggerMainComponent() {}

  public static Builder builder() {
    return new Builder();
  }

  public static MainComponent create() {
    return new Builder().build();
  }

  private VideoInfoParserImpl getVideoInfoParserImpl() {
    return new VideoInfoParserImpl(
        DownloadModule_UrlToStringLoaderFactory.proxyUrlToStringLoader());
  }

  private VideoInfoParser getVideoInfoParser() {
    return DataModule_MVideoInfoParserFactory.proxyMVideoInfoParser(getVideoInfoParserImpl());
  }

  private RunnerImpl getRunnerImpl() {
    return new RunnerImpl(
        DownloadModule_ProvideHeaterFactory.proxyProvideHeater(), getVideoInfoParser());
  }

  @Override
  public Runner mainAppRunner() {
    return RunnerModule_MainRunnerFactory.proxyMainRunner(getRunnerImpl());
  }

  public static final class Builder {
    private Builder() {}

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder downloadModule(DownloadModule downloadModule) {
      Preconditions.checkNotNull(downloadModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder runnerModule(RunnerModule runnerModule) {
      Preconditions.checkNotNull(runnerModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder dataModule(DataModule dataModule) {
      Preconditions.checkNotNull(dataModule);
      return this;
    }

    public MainComponent build() {
      return new DaggerMainComponent();
    }
  }
}
