package com.s13g.atvdl;

import java.io.IOException;

/**
 * Main entry point of the app.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    DaggerMainComponent.create().mainAppRunner().run(args);
  }
}
