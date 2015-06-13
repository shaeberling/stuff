package com.s13g.themetools.keystyler.controller;

import com.s13g.themetools.keystyler.model.Theme;

import java.io.File;
import java.util.Optional;

/**
 * The main controller interface.
 */
public interface MainController {
  void onThemeFolderSelected(Optional<File> themeFolder);
  void onThemeSelected(Theme theme);
}
