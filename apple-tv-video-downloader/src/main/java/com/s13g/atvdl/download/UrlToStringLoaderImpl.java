package com.s13g.atvdl.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Default implementation of {@link UrlToStringLoader}.
 */
public class UrlToStringLoaderImpl implements UrlToStringLoader {
  @Override
  public String loadUrl(String urlStr) throws IOException {
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
