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

import java.util.HashMap;
import java.util.Map;

/**
 * This is the model created from the parsed skins.xml. I haven't yet found a complete XML schema
 * for this, so for now this will cover all the ones I come across, which might not include all
 * valid specifications.
 * <p>
 * An example can be found here: http://www.dexilog.com/smartkeyboard/wiki/Open_Skin_Format
 */
public class ThemeStyle {
  public enum Entry {
    /** NOTE: According to the example the background can also be defined as two colors. */
    BACKGROUND_IMAGE,
    KEY_BACKGROUND_NORMAL,
    KEY_BACKGROUND_PRESSED,
    MOD_KEY_BACKGROUND_NORMAL,
    MOD_KEY_BACKGROUND_PRESSED,
    MOD_KEY_BACKGROUND_NORMAL_OFF,
    MOD_KEY_BACKGROUND_PRESSED_OFF,
    MOD_KEY_BACKGROUND_NORMAL_ON,
    MOD_KEY_BACKGROUND_PRESSED_ON,
    SYMBOLS_DELETE,
    SYMBOLS_RETURN,
    SYMBOLS_SEARCH,
    SYMBOLS_SHIFT,
    SYMBOLS_SHIFT_LOCKED,
    SYMBOLS_SPACE,
    SYMBOLS_MIC,
    COLORS_LABEL,
    COLORS_ALT_LABEL,
    COLORS_MOD_LABEL
  }

  /**
   * Class holding data about the theme style items.
   */
  public static class Item {
    public String name;
    public byte[] data;
    public boolean hdpi;
  }

  /** The map of items extracted from the skins.xml file. */
  private final Map<Entry, Item> mItems = new HashMap<>();

  /**
   * Sets the name of the item.
   *
   * @param entry which entry to set the name for.
   * @param name  the name of the item.
   */
  public void setItemName(Entry entry, String name) {
    getItem(entry).name = name;
  }

  /**
   * Returns the items data for the given entry.
   *
   * @param entry the entry for which to get the data for.
   * @return The data for the given entry, if present.
   */
  public byte[] getItemData(Entry entry) {
    return getItem(entry).data;
  }

  /**
   * @return A map keys by the name of the entry.
   */
  public Map<String, Item> getReversedMap(String prefix) {
    Map<String, Item> reversedMap = new HashMap<>();
    mItems.values().forEach((item) -> {
      if (item.name != null) {
        reversedMap.put(prefix + item.name, item);
      }
    });
    return reversedMap;
  }

  private Item getItem(Entry entry) {
    if (!mItems.containsKey(entry)) {
      mItems.put(entry, new Item());
    }
    return mItems.get(entry);
  }
}
