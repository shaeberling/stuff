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

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Is able to load keyboard themes from ZIP files.
 */
public class ThemeLoader {
  private static SAXBuilder sSaxBuilder = new SAXBuilder();
  private final File mThemeFile;

  public static ThemeLoader create(File themeFile) {
    return new ThemeLoader(themeFile);
  }

  private ThemeLoader(File themeFile) {
    mThemeFile = themeFile;
  }

  /**
   * Loads the theme.
   *
   * @return Returns the theme if it could be parsed or empty, if not.
   */
  public Optional<Theme> load() {
    try {
      ZipInputStream zin = new ZipInputStream(new FileInputStream(mThemeFile));
      ZipEntry entry;
      while ((entry = zin.getNextEntry()) != null) {
        if (entry.getName().equals("skin.xml")) {
          String themeName = getThemeName(mThemeFile.getName(), zin);
          if (themeName != null) {
            return Optional.of(new Theme(themeName, mThemeFile));
          }
          break;
        }
      }
    } catch (JDOMException e) {
      System.err.println("Could not parse XML file: " + e.getMessage() + " - " + mThemeFile);
    } catch (FileNotFoundException e) {
      System.err.println("Could not find file: " + e.getMessage() + " - " + mThemeFile);
    } catch (IOException e) {
      System.err.println("Could not read file: " + e.getMessage() + " - " + mThemeFile);
    }
    return Optional.empty();
  }

  private static String getThemeName(String filename, InputStream skinsXml)
          throws JDOMException, IOException {
    Document document = sSaxBuilder.build(skinsXml);
    if (document == null) {
      System.err.println("Could not get document - " + filename);
      return null;
    }

    Element root = document.getRootElement();
    if (root == null) {
      System.err.println("Could not get root element - " + filename);
      return null;
    }
    return root.getAttributeValue("name");
  }

}
