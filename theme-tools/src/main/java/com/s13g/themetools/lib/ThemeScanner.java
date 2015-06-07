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

package com.s13g.themetools.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Scans a given directory for themes.
 */
public class ThemeScanner {
  private static final String THEME_FILE_SUFFIX = ".zip";
  private final File mThemeRoot;

  public static Optional<ThemeScanner> create(File themeRoot) {
    if (!themeRoot.exists() || !themeRoot.isDirectory()) {
      System.err.println("Given argument is not a valid directory:" + themeRoot.getAbsolutePath());
      return Optional.empty();
    }
    return Optional.of(new ThemeScanner(themeRoot));
  }

  private ThemeScanner(File themeRoot) {
    mThemeRoot = themeRoot;
  }

  /**
   * Scan the given theme root for valid themes.
   *
   * @param sortByName whether to sort the themes by name.
   * @return A list of all themes that were successfully parsed.
   */
  public List<Theme> scan(boolean sortByName) {
    File[] zipFiles = mThemeRoot.listFiles((dir, name) -> name.toLowerCase().endsWith
            (THEME_FILE_SUFFIX));
    if (zipFiles.length == 0) {
      System.err.println("No theme files found in " + mThemeRoot.getAbsolutePath());
      return Collections.emptyList();
    }

    System.out.println(zipFiles.length + " theme files found.");
    List<Theme> themes = new ArrayList<>();

    for (File file : zipFiles) {
      Optional<Theme> theme = ThemeLoader.create(file).load();
      if (theme.isPresent()) {
        themes.add(theme.get());
      }
    }
    if (sortByName) {
      Collections.sort(themes, (one, two) -> one.name.compareTo(two.name));
    }
    return themes;
  }
}
