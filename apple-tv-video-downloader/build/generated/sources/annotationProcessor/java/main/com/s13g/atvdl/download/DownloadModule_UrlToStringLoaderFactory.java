package com.s13g.atvdl.download;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DownloadModule_UrlToStringLoaderFactory implements Factory<UrlToStringLoader> {
  private static final DownloadModule_UrlToStringLoaderFactory INSTANCE =
      new DownloadModule_UrlToStringLoaderFactory();

  @Override
  public UrlToStringLoader get() {
    return proxyUrlToStringLoader();
  }

  public static DownloadModule_UrlToStringLoaderFactory create() {
    return INSTANCE;
  }

  public static UrlToStringLoader proxyUrlToStringLoader() {
    return Preconditions.checkNotNull(
        DownloadModule.urlToStringLoader(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
