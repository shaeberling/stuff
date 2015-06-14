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

import com.s13g.themetools.keystyler.KeyStylerApp;

import java.util.Optional;
import java.util.prefs.Preferences;

/**
 * Is able to persist and read settings.
 */
public class SettingsController {
  private enum Setting {
    SKIN_ROOT_PATH
  }

  Preferences mPreferences;

  public SettingsController() {
    mPreferences = Preferences.userRoot().node(KeyStylerApp.class.getName());
  }

  public Optional<String> getSkinRoot() {
    return Optional.ofNullable(mPreferences.get(Setting.SKIN_ROOT_PATH.name(), null));
  }

  public void setSkinRoot(String path) {
    mPreferences.put(Setting.SKIN_ROOT_PATH.name(), path);
  }
}
