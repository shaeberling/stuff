
package com.s13g.atvdl.runner;

import com.s13g.atvdl.data.VideoInfo;
import com.s13g.atvdl.data.VideoInfoParser;
import com.s13g.atvdl.download.Downloader;
import com.s13g.atvdl.download.UrlToStringLoader;
import org.json.JSONArray;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;


public class RunnerImpl implements Runner {
  private static final String JSON_URL = "http://a1.phobos.apple" +
   ".com/us/r1000/000/Features/atv/AutumnResources/videos/entries.json";

  private final Downloader mDownloader;
  private final VideoInfoParser mVideoInfoParser;

  @Inject
  public RunnerImpl(Downloader downloader,
                    VideoInfoParser videoInfoParser) {
    mDownloader = downloader;
    mVideoInfoParser = videoInfoParser;
  }

  @Override
  public void run(String[] args) throws IOException {
    checkArgument(args.length == 1, "Invalid number of arguments.");
    File destination = new File(args[0]);

    if (destination.exists() && !destination.isDirectory()) {
      throw new IOException("Destination is not a directory: " + destination.getAbsolutePath());
    }

    if (!destination.exists() && !destination.mkdirs()) {
      throw new IOException("Cannot create destination: " + destination.getAbsolutePath());
    }

    List<VideoInfo> videos = mVideoInfoParser.getAllVideoInfos(JSON_URL);
    System.out.println("Videos: " + videos.size());

    for (VideoInfo video : videos) {
      mDownloader.download(video.uri, new File(destination, video.label + ".mov"));
    }
  }
}