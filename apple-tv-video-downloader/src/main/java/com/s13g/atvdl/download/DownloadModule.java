package com.s13g.atvdl.download;

import dagger.Module;
import dagger.Provides;

@Module
public class DownloadModule {
  @Provides
  static Downloader provideHeater() {
    return new DownloaderImpl();
  }

  @Provides
  static UrlToStringLoader urlToStringLoader() {
    return new UrlToStringLoaderImpl();
  }
}
