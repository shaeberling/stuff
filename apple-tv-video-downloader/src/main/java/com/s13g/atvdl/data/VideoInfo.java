package com.s13g.atvdl.data;

/**
 * Video metadata.
 */
public class VideoInfo {
  public final String uri;
  public final String label;

  public VideoInfo(String uri, String label) {
    this.uri = uri;
    this.label = label;
  }
}