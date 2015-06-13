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

package com.s13g.themetools.keystyler.view;

import com.s13g.themetools.keystyler.controller.MainController;
import com.s13g.themetools.keystyler.model.Theme;
import com.s13g.themetools.keystyler.model.ThemeStyle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * The controller for the main keystyler windows JavaFX controls.
 */
public class MainFxView implements MainViewInterface {
  @FXML
  ListView<Theme> mThemeListView;

  @FXML
  Label mLabelSelectedTheme;

  @FXML
  ImageView mDebugBackground;

  private Stage mStage;

  private MainController mController;

  /**
   * Initializes the JavaFX controller.
   *
   * @param stage the main stage instance.
   */
  public void initialize(Stage stage) {
    mStage = stage;
    mThemeListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> mController.onThemeSelected(newValue));
  }

  /** Called from FX. */
  public void onChooseFolderButtonClick() {
    mController.onThemeFolderSelected(showDirectoryChooser());
  }

  @Override
  public void setController(MainController controller) {
    mController = controller;
  }

  @Override
  public void setScannedThemes(List<Theme> themes) {
    Platform.runLater(() -> mThemeListView.getItems().addAll(themes));
  }

  private Optional<File> showDirectoryChooser() {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle(UiMessages.OPEN_THEME_FOLDER);
    return Optional.ofNullable(chooser.showDialog(mStage));
  }

  @Override
  public void setSelectedTheme(Theme theme) {
    mLabelSelectedTheme.setText(theme.toString());

    Image backgroundImage = new Image(new ByteArrayInputStream(
            theme.style.getItemData(ThemeStyle.Entry.BACKGROUND_IMAGE)));
    mDebugBackground.setImage(backgroundImage);
    mDebugBackground.setFitWidth(backgroundImage.getWidth());
    mDebugBackground.setFitHeight(backgroundImage.getHeight());
  }
}
