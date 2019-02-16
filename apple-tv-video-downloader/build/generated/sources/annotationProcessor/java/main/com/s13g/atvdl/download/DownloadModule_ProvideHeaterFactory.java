package com.s13g.atvdl.download;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DownloadModule_ProvideHeaterFactory implements Factory<Downloader> {
  private static final DownloadModule_ProvideHeaterFactory INSTANCE =
      new DownloadModule_ProvideHeaterFactory();

  @Override
  public Downloader get() {
    return proxyProvideHeater();
  }

  public static DownloadModule_ProvideHeaterFactory create() {
    return INSTANCE;
  }

  public static Downloader proxyProvideHeater() {
    return Preconditions.checkNotNull(
        DownloadModule.provideHeater(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
