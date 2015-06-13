/*
 * Copyright 2015 Sascha Haeberling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.s13g.themetools.keystyler.controller;

import com.s13g.themetools.keystyler.model.Theme;
import com.s13g.themetools.keystyler.view.MainViewInterface;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * In MVC fashion, this is the controller.
 */
public class MainControllerImpl implements MainController {

  private final MainViewInterface mView;

  public MainControllerImpl(MainViewInterface view) {
    mView = view;
    mView.setController(this);
  }

  @Override
  public void onThemeFolderSelected(Optional<File> themeFolder) {
    if (!themeFolder.isPresent()) {
      System.out.println("No directory selected.");
      return;
    }

    System.out.println("Root: " + themeFolder.get().getAbsolutePath());
    Executors.newSingleThreadExecutor().execute(() -> {
      Optional<ThemeScanner> scanner = ThemeScanner.create(themeFolder.get());
      mView.setScannedThemes(scanner.get().scan(true /*sortByName*/));
    });
  }

  @Override
  public void onThemeSelected(Theme theme) {
    mView.setSelectedTheme(theme);
  }
}
