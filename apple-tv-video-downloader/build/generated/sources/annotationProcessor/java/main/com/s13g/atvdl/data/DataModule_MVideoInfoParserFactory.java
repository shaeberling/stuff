package com.s13g.atvdl.data;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataModule_MVideoInfoParserFactory implements Factory<VideoInfoParser> {
  private final Provider<VideoInfoParserImpl> parserProvider;

  public DataModule_MVideoInfoParserFactory(Provider<VideoInfoParserImpl> parserProvider) {
    this.parserProvider = parserProvider;
  }

  @Override
  public VideoInfoParser get() {
    return proxyMVideoInfoParser(parserProvider.get());
  }

  public static DataModule_MVideoInfoParserFactory create(
      Provider<VideoInfoParserImpl> parserProvider) {
    return new DataModule_MVideoInfoParserFactory(parserProvider);
  }

  public static VideoInfoParser proxyMVideoInfoParser(VideoInfoParserImpl parser) {
    return Preconditions.checkNotNull(
        DataModule.mVideoInfoParser(parser),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
