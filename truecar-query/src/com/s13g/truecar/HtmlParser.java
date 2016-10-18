/*
 * Copyright 2016 Sascha Häberling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.s13g.truecar;

import org.json.JSONObject;

import java.util.Optional;

/**
 * Is able to get the JSON from the truecar website HTML.
 * <p>
 * Note: This needs to get adapted every time their website changes.
 */
class HtmlParser {
  private static final String START_TOKEN = "window.__INITIAL_STATE__=";
  private static final String END_TOKEN = ";window.__GS_ENV_VARS__";

  private final String mHtml;

  HtmlParser(String html) {
    mHtml = html;
  }

  Optional<JSONObject> parseJson() {
    System.out.println("Parsing loaded HTML: " + mHtml.length());
    int start = mHtml.indexOf(START_TOKEN);
    if (start < 0) {
      System.err.println("Cannot find start token");
      return Optional.empty();
    }

    int end = mHtml.indexOf(END_TOKEN, start);
    if (end < 0) {
      System.err.println("Cannot find end token");
      return Optional.empty();
    }
    String json = mHtml.substring(start + START_TOKEN.length(), end);
    return Optional.of(new JSONObject(json));
  }
}
