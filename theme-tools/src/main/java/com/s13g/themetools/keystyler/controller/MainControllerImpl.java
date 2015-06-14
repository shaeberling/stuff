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
import com.s13g.themetools.keystyler.util.Listener;
import com.s13g.themetools.keystyler.view.MainViewInterface;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * In MVC fashion, this is the controller.
 */
public class MainControllerImpl implements MainController {

  private final MainViewInterface mView;
  private final SettingsController mSettingsController;

  private Listener<String> mSkinRootChangedListener;

  public MainControllerImpl(MainViewInterface view, SettingsController settingsController) {
    mView = view;
    mView.setController(this);
    mSettingsController = settingsController;
  }

  @Override
  public void onCreate() {
    Optional<String> skinRoot = mSettingsController.getSkinRoot();
    if (skinRoot.isPresent()) {
      aysncScanThemesAndUpdateView(new File(skinRoot.get()));
    }
  }

  @Override
  public void onThemeFolderSelected(Optional<File> themeFolder) {
    if (!themeFolder.isPresent()) {
      System.out.println("No directory selected.");
      return;
    }

    mSettingsController.setSkinRoot(themeFolder.get().getAbsolutePath());
    System.out.println("Root: " + themeFolder.get().getAbsolutePath());
    aysncScanThemesAndUpdateView(themeFolder.get());
  }

  @Override
  public void onThemeSelected(Theme theme) {
    mView.setSelectedTheme(theme);
  }

  @Override
  public void setSkinRootChangedListener(Listener<String> listener) {
    mSkinRootChangedListener = listener;
  }

  private void aysncScanThemesAndUpdateView(File themeFolder) {
    if (mSkinRootChangedListener != null) {
      mSkinRootChangedListener.onCallback(themeFolder.getAbsolutePath());
    }

    Executors.newSingleThreadExecutor().execute(() -> {
      Optional<ThemeScanner> scanner = ThemeScanner.create(themeFolder);
      mView.setScannedThemes(scanner.get().scan(true /*sortByName*/));
    });
  }
}
