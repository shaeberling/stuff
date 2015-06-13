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

/**
 * This is the model created from the parsed skins.xml. I haven't yet found a complete XML schema
 * for this, so for now this will cover all the ones I come across, which might not include all
 * valid specifications.
 * <p>
 * An example can be found here: http://www.dexilog.com/smartkeyboard/wiki/Open_Skin_Format
 */
public class ThemeStyle {
  /**
   * The background image.
   * NOTE: According to the example the background can also be defined as two colors.
   */
  public String backgroundImage;

  // Key-Background
  public String keyBackgroundNormal;
  public String keyBackgroundPressed;

  // Mod-Key-Background
  public String modKeyBackgroundNormal;
  public String modKeyBackgroundPressed;
  public String modKeyBackgroundNormalOff;
  public String modKeyBackgroundPressedOff;
  public String modKeyBackgroundNormalOn;
  public String modKeyBackgroundPressedOn;

  // Symbols
  public String symbolsDelete;
  public String symbolsReturn;
  public String symbolsSearch;
  public String symbolsShift;
  public String symbolsShiftLocked;
  public String symbolsSpace;
  public String symbolsMic;

  // Colors
  public String colorsLabel;
  public String colorsAltLabel;
  public String colorsModLabel;
}
