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
import com.s13g.themetools.keystyler.controller.SettingsController;
import com.s13g.themetools.keystyler.view.ViewModule;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * Main application class for KeyStylerApp.
 */
public class KeyStylerApp extends Application {
  public static final String APP_TITLE = "Keyboard Styler";

  private Stage mStage;

  @Inject
  MainController mMainController;
  @Inject
  SettingsController mSettingsController;
  @Inject
  Scene mMainScene;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    KeyStylerComponent component =
            DaggerKeyStylerComponent.builder().viewModule(new ViewModule(stage)).build();
    component.inject(this);

    mStage = stage;

    // Make sure we exit the application when the main stage closes.
    mStage.setOnCloseRequest((e) -> destroy());
    mStage.setTitle(APP_TITLE);
    mStage.setScene(mMainScene);
    mStage.show();

    // Change title of app.
    mMainController.setSkinRootChangedListener((skinRoot) -> {
      mStage.setTitle(KeyStylerApp.APP_TITLE + " - [" + skinRoot + "]");
    });

    mMainController.onCreate();
  }

  private void destroy() {
    Platform.exit();
    System.exit(0);
  }
}
