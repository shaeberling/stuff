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

import com.s13g.themetools.keystyler.model.Theme;
import com.s13g.themetools.keystyler.model.ThemeStyle;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
          return Optional.ofNullable(parse(mThemeFile, zin));
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

  /**
   * Parses the ZIP file and the skins.xml therein to produce the final Theme model file.
   *
   * @param themeFile the file that contains the theme.
   * @param skinsXml  the skinsXML stream that contains the skin specification.
   * @return The valid Theme model, if the file could be parses, null otherwise.
   * @throws JDOMException if the skins.xml cannot be parsed.
   * @throws IOException   if the theme file could not be read,
   */
  private static Theme parse(File themeFile, InputStream skinsXml)
          throws JDOMException, IOException {
    Document document = sSaxBuilder.build(skinsXml);
    if (document == null) {
      System.err.println("Could not get document - " + themeFile.getAbsolutePath());
      return null;
    }

    Element root = document.getRootElement();
    if (root == null) {
      System.err.println("Could not get root element - " + themeFile.getAbsolutePath());
      return null;
    }

    String name = root.getAttributeValue("name");
    ThemeStyle style = new ThemeStyle();

    Element background = root.getChild("background");
    Element backgroundImage = background.getChild("image");
    // NOTE: According to the example, background can also have two colors defined instead of an
    // image. This currently does not support such themes. So we simply don't supply a background;
    if (backgroundImage != null) {
      style.backgroundImage = backgroundImage.getText();
    } else {
      System.err.println("Theme does not have background/image. Skipping.");
    }

    Element keyBackground = root.getChild("key-background");
    style.keyBackgroundNormal = keyBackground.getChildText("normal");
    style.keyBackgroundPressed = keyBackground.getChildText("pressed");

    Element modKeyBackground = root.getChild("mod-key-background");
    style.modKeyBackgroundNormal = modKeyBackground.getChildText("normal");
    style.modKeyBackgroundPressed = modKeyBackground.getChildText("pressed");
    style.modKeyBackgroundNormalOff = modKeyBackground.getChildText("normal-off");
    style.modKeyBackgroundPressedOff = modKeyBackground.getChildText("pressed-off");
    style.modKeyBackgroundNormalOn = modKeyBackground.getChildText("normal-on");
    style.modKeyBackgroundPressedOn = modKeyBackground.getChildText("pressed-on");

    Element symbols = root.getChild("symbols");
    style.symbolsDelete = symbols.getChildText("delete");
    style.symbolsReturn = symbols.getChildText("return");
    style.symbolsSearch = symbols.getChildText("search");
    style.symbolsShift = symbols.getChildText("shift");
    style.symbolsShiftLocked = symbols.getChildText("shift-locked");
    style.symbolsSpace = symbols.getChildText("space");
    style.symbolsMic = symbols.getChildText("mic");

    Element colors = root.getChild("colors");
    style.colorsLabel = colors.getChildText("label");
    style.colorsAltLabel = colors.getChildText("alt-label");
    style.colorsModLabel = colors.getChildText("mod-label");

    return new Theme(name, themeFile, style);
  }

}
