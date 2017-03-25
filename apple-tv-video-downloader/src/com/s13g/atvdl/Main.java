
package com.s13g.atvdl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Main {
  private static final String JSON_URL = "http://a1.phobos.apple.com/us/r1000/000/Features/atv/AutumnResources/videos/entries.json";

  private static class VideoInfo {
    private final String uri;
    private final String label;

    private VideoInfo(String uri, String label) {
      this.uri = uri;
      this.label = label;
    }
  }

  public static void main(String[] args) throws IOException {
    checkArgument(args.length == 1, "Invalid number of arguments.");
    File destination = new File(args[0]);
    if (destination.exists() && !destination.isDirectory()) {
      throw new IOException("Destination is not a directory: " + destination.getAbsolutePath());
    }

    if (!destination.exists() && !destination.mkdirs()) {
      throw new IOException("Cannot create destination: " + destination.getAbsolutePath());
    }

    String jsonData = loadUrl(JSON_URL);
    JSONArray entries = new JSONArray(jsonData);
    System.out.println("Entries: " + entries.length());
    List<VideoInfo> videos = getAllVideoInfos(entries);
    System.out.println("Videos: " + videos.size());

    for (VideoInfo video : videos) {
      download(video.uri, new File(destination, video.label + ".mov"));
    }
  }

  private static void download(String uri, File destination) throws IOException {
    URL url = new URL(uri);
    try (InputStream is = url.openConnection().getInputStream();
         OutputStream os = new FileOutputStream(destination)) {
      System.out.println("Downloading to " + destination + " ...");
      long bytes = ByteStreams.copy(is, os);
      System.out.println("Downloaded " + bytes + " bytes.");
    }
  }

  private static List<VideoInfo> getAllVideoInfos(JSONArray entries) {
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

  private static String loadUrl(String urlStr) throws IOException {
    System.out.println("Downloading " + urlStr);
    URL url = new URL(urlStr);
    try (InputStream is = url.openConnection().getInputStream();
         InputStreamReader reader = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(reader)) {
      StringBuilder json = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        json.append(line);
      }
      String jsonStr = json.toString();
      System.out.println("Done downloading: " + jsonStr.length());
      return jsonStr;
    }
  }
}