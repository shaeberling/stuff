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

package com.s13g.themetools;

import com.s13g.themetools.lib.Theme;
import com.s13g.themetools.lib.ThemeScanner;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Read ZIP files containing keyboard themes. Then read the skins.xml within the
 * ZIP file and read the name of the theme.
 * <p>
 * Usage: KeyboardSkinNameFinderCLI 'path containing ZIP files'
 */
public class KeyboardSkinNameFinderCLI {
  private static boolean SORT_BY_THEME_NAME = true;

  public static void main(String[] args) throws IOException, JDOMException {
    if (args.length != 1) {
      System.err.println("No path provided");
      return;
    }
    Optional<ThemeScanner> scanner = ThemeScanner.create(new File(args[0]));
    if (!scanner.isPresent()) {
      return;
    }

    List<Theme> themes = scanner.get().scan(SORT_BY_THEME_NAME);
    printMapFormatted(themes);
  }

  private static void printMapFormatted(List<Theme> namePairs) {
    int longestFileNameLength = 0;
    for (Theme namePair : namePairs) {
      longestFileNameLength = Math.max(namePair.location.getName().length(),
              longestFileNameLength);
    }

    for (Theme namePair : namePairs) {
      String name = namePair.location.getName();
      int lengthDiff = longestFileNameLength - name.length();
      String filler = createFiller(lengthDiff);
      String themeName = namePair.name;
      System.out.println(name + filler + themeName);
    }
  }

  private static String createFiller(int length) {
    StringBuilder filler = new StringBuilder();
    filler.append(' ');
    for (int i = 0; i < length; ++i) {
      filler.append('-');
    }
    filler.append("-> ");
    return filler.toString();
  }
}
