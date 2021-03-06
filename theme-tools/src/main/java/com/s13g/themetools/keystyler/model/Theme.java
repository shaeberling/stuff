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

package com.s13g.themetools.keystyler.model;

import java.io.File;

/**
 * This is a single keyboard theme.
 */
public class Theme {
  public final String name;
  public final File location;
  public final ThemeStyle style;

  public Theme(String name, File location, ThemeStyle style) {
    this.name = name;
    this.location = location;
    this.style = style;
  }

  @Override
  public String toString() {
    return name + " (" + location.getName() + ")";
  }
}
