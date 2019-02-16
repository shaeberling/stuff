package com.s13g.atvdl.data;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
  @Provides
  static VideoInfoParser mVideoInfoParser(VideoInfoParserImpl parser) {
    return parser;
  }
}
