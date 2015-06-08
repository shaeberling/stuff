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

import com.s13g.themetools.keystyler.model.Theme;
import com.s13g.themetools.keystyler.util.Listener;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * The main 'view' interface for the MVC architecture.
 */
public interface MainViewInterface {
  Optional<File> showDirectoryChooser();

  void setChooseFolderButtonListener(Listener listener);

  void setScannedThemes(List<Theme> themes);
}
