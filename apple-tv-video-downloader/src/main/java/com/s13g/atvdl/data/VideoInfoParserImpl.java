package com.s13g.atvdl.data;

import com.google.common.collect.ImmutableList;
import com.s13g.atvdl.download.UrlToStringLoader;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link VideoInfoParser}.
 */
public class VideoInfoParserImpl implements VideoInfoParser {
  private final UrlToStringLoader mUrlToStringLoader;

  @Inject
  public VideoInfoParserImpl(UrlToStringLoader urlToStringLoader) {
    mUrlToStringLoader = urlToStringLoader;
  }

  @Override
  public List<VideoInfo> getAllVideoInfos(String jsonUrl) {
    try {
      String jsonData = mUrlToStringLoader.loadUrl(jsonUrl);
      return parse(new JSONArray(jsonData));
    } catch (IOException e) {
      return ImmutableList.copyOf(new ArrayList<>());
    }
  }

  private List<VideoInfo> parse(JSONArray entries) {
    System.out.println("Entries: " + entries.length());
    ArrayList<VideoInfo> allUrls = new ArrayList<>();
    for (int i = 0; i < entries.length(); ++i) {
      JSONArray subEntries = entries.getJSONObject(i).getJSONArray("assets");
      for (int j = 0; j < subEntries.length(); ++j) {
        JSONObject entry = subEntries.getJSONObject(j);
        String url = entry.getString("url");
        String label = entry.getString("accessibilityLabel");
        String id = entry.getString("id");
        allUrls.add(new VideoInfo(url, label + "_" + id));
      }
    }
    return ImmutableList.copyOf(allUrls);
  }
}
