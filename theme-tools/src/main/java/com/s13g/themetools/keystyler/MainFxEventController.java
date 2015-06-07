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

package com.s13g.themetools.keystyler;

import com.s13g.themetools.lib.Theme;
import com.s13g.themetools.lib.ThemeScanner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * The controller for the main keystyler windows JavaFX controls.
 */
public class MainFxEventController {
  @FXML
  ListView<Theme> mThemeListView;
  private ViewController mViewController;

  /** Called from FX. */
  public void onChooseFolderButtonClick() {
    Optional<File> root = mViewController.getUserChosenThemeRoot();
    if (root.isPresent()) {
      System.out.println("Root: " + root.get().getAbsolutePath());
      System.out.println("Doe we have the list? " + mThemeListView);
      asyncScanThemes(root.get());
    } else {
      System.out.println("No directory selected.");
    }
  }

  private void asyncScanThemes(File root) {
    Executors.newSingleThreadExecutor().execute(() -> {
      Optional<ThemeScanner> scanner = ThemeScanner.create(root);
      List<Theme> themes = scanner.get().scan(true /*sortByName*/);
      Platform.runLater(() -> mThemeListView.getItems().addAll(themes));
    });
  }

  public void setStage(ViewController viewController) {
    mViewController = viewController;
  }
}
