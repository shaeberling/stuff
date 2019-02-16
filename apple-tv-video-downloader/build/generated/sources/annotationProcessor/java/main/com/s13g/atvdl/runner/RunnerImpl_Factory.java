package com.s13g.atvdl.runner;

import com.s13g.atvdl.data.VideoInfoParser;
import com.s13g.atvdl.download.Downloader;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RunnerImpl_Factory implements Factory<RunnerImpl> {
  private final Provider<Downloader> downloaderProvider;

  private final Provider<VideoInfoParser> videoInfoParserProvider;

  public RunnerImpl_Factory(
      Provider<Downloader> downloaderProvider, Provider<VideoInfoParser> videoInfoParserProvider) {
    this.downloaderProvider = downloaderProvider;
    this.videoInfoParserProvider = videoInfoParserProvider;
  }

  @Override
  public RunnerImpl get() {
    return new RunnerImpl(downloaderProvider.get(), videoInfoParserProvider.get());
  }

  public static RunnerImpl_Factory create(
      Provider<Downloader> downloaderProvider, Provider<VideoInfoParser> videoInfoParserProvider) {
    return new RunnerImpl_Factory(downloaderProvider, videoInfoParserProvider);
  }

  public static RunnerImpl newRunnerImpl(Downloader downloader, VideoInfoParser videoInfoParser) {
    return new RunnerImpl(downloader, videoInfoParser);
  }
}
