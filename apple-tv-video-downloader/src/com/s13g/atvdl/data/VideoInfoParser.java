package com.s13g.atvdl.data;

import org.json.JSONArray;

import java.util.List;

/**
 * Parses JSON data to produce a list of {@link VideoInfo} instances.
 */
public interface VideoInfoParser {
  List<VideoInfo> getAllVideoInfos(String url);
}
