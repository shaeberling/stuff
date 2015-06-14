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

import com.s13g.themetools.keystyler.controller.MainController;
import com.s13g.themetools.keystyler.controller.MainControllerImpl;
import com.s13g.themetools.keystyler.controller.SettingsController;
import com.s13g.themetools.keystyler.view.MainFxView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for KeyStylerApp.
 */
public class KeyStylerApp extends Application {
  private static final String APP_TITLE = "Keyboard Styler";

  private Stage mStage;

  private MainController mMainController;
  private SettingsController mSettingsController;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    mStage = stage;

    // Make sure we exit the application when the main stage closes.
    mStage.setOnCloseRequest((e) -> destroy());

    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/keystyler.fxml"));
    Parent root = loader.load();
    MainFxView mainFxView = (MainFxView) loader.getController();
    mainFxView.initialize(stage);

    mSettingsController = new SettingsController();
    mMainController = new MainControllerImpl(mainFxView, mSettingsController);
    mMainController.setSkinRootChangedListener((skinRoot) -> setStageTitleWithPath(skinRoot));

    Scene mainScene = new Scene(root, 800, 600);
    mStage.setTitle(APP_TITLE);
    mStage.setScene(mainScene);
    mStage.show();

    mMainController.onCreate();
  }

  private void setStageTitleWithPath(String path) {
    mStage.setTitle(APP_TITLE + " - [" + path + "]");
  }

  private void destroy() {
    Platform.exit();
    System.exit(0);
  }
}
