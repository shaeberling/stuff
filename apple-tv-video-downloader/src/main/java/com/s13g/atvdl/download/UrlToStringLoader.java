package com.s13g.atvdl.download;

import java.io.IOException;

/**
 * Load a URL as a string.
 */
public interface UrlToStringLoader {
  String loadUrl(String urlStr) throws IOException;
}
