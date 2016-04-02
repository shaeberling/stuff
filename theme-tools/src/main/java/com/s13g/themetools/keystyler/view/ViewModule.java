/*
 * Copyright 2015 Sascha Haeberling
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.s13g.themetools.keystyler.view;

import com.s13g.themetools.keystyler.annotations.ForApplication;
import dagger.Module;
import dagger.Provides;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

@Module
public class ViewModule {
  private final Stage mStage;

  public ViewModule(Stage stage) {
    mStage = stage;
  }

  @Provides
  @ForApplication
  public FXMLLoaderProxy provideMainFxView() {
    try {
      return new FXMLLoaderProxy(new FXMLLoader(getClass().getResource("keystyler.fxml")), mStage);
    } catch (IOException e) {
      System.err.println("Could not load main layout file.");
      return null;
    }
  }

  @Provides
  @ForApplication
  public MainViewInterface provideMainViewInterface(FXMLLoaderProxy loader) {
    return loader.getMainFxView();
  }

  @Provides
  @ForApplication
  public Scene provideMainScene(FXMLLoaderProxy loader) {
    return new Scene(loader.getParent(), 800, 600);
  }

  @Provides
  @ForApplication
  public Stage provideStage() {
    return mStage;
  }


}
