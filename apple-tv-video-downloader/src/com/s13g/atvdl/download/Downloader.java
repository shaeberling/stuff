package com.s13g.atvdl.download;

import dagger.Component;

import java.io.File;
import java.io.IOException;

/**
 * Classes implementing this interface can download a URL to a local file.
 */
public interface Downloader {
  /**
   * Downloads a file.
   *
   * @param uri         the URL of the file to download.
   * @param destination where on the local file system to store the downloaded data.
   * @throws IOException thrown if the file cannot be downloaded.
   */
  void download(String uri, File destination) throws IOException;
}
