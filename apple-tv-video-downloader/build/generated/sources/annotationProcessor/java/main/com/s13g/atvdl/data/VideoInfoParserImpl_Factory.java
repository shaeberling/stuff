package com.s13g.atvdl.data;

import com.s13g.atvdl.download.UrlToStringLoader;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class VideoInfoParserImpl_Factory implements Factory<VideoInfoParserImpl> {
  private final Provider<UrlToStringLoader> urlToStringLoaderProvider;

  public VideoInfoParserImpl_Factory(Provider<UrlToStringLoader> urlToStringLoaderProvider) {
    this.urlToStringLoaderProvider = urlToStringLoaderProvider;
  }

  @Override
  public VideoInfoParserImpl get() {
    return new VideoInfoParserImpl(urlToStringLoaderProvider.get());
  }

  public static VideoInfoParserImpl_Factory create(
      Provider<UrlToStringLoader> urlToStringLoaderProvider) {
    return new VideoInfoParserImpl_Factory(urlToStringLoaderProvider);
  }

  public static VideoInfoParserImpl newVideoInfoParserImpl(UrlToStringLoader urlToStringLoader) {
    return new VideoInfoParserImpl(urlToStringLoader);
  }
}
