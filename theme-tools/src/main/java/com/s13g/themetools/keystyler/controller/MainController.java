package com.s13g.themetools.keystyler.controller;

import com.s13g.themetools.keystyler.model.Theme;
import com.s13g.themetools.keystyler.util.Listener;

import java.io.File;
import java.util.Optional;

/**
 * The main controller interface.
 */
public interface MainController {
  void onCreate();
  void onThemeFolderSelected(Optional<File> themeFolder);
  void onThemeSelected(Theme theme);
  void setSkinRootChangedListener(Listener<String> listener);
}
