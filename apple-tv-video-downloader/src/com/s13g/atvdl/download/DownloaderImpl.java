package com.s13g.atvdl.download;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * The default {@link Downloader} implementation.
 */
public class DownloaderImpl implements Downloader {
  @Override
  public void download(String uri, File destination) throws IOException {
    URL url = new URL(uri);
    try (InputStream is = url.openConnection().getInputStream();
         OutputStream os = new FileOutputStream(destination)) {
      System.out.println("Downloading to " + destination + " ...");
      long bytes = ByteStreams.copy(is, os);
      System.out.println("Downloaded " + bytes + " bytes.");
    }
  }
}
