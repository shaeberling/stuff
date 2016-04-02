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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by Sascha on 8/14/2015.
 */
public class FXMLLoaderProxy {
  private final Parent mParent;
  private final MainFxView mMainFxView;

  public FXMLLoaderProxy(FXMLLoader loader, Stage stage) throws IOException {
    mParent = loader.load();
    mMainFxView = loader.getController();
    mMainFxView.initialize(stage);
  }

  public Parent getParent() {
    return mParent;
  }

  public MainFxView getMainFxView() {
    return mMainFxView;
  }
}
