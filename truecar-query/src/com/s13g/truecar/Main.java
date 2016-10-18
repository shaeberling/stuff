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

import com.s13g.truecar.CarRequestConfig.Maker;
import com.s13g.truecar.CarRequestConfig.Model;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
  private static final String START_TOKEN = "window.__INITIAL_STATE__=";
  private static final String END_TOKEN = ";window.__GS_ENV_VARS__";

  private static List<CarRequestConfig> createWatches() {
    List<CarRequestConfig> watches = new ArrayList<>();
    watches.add(new CarRequestConfig(Maker.VW, Model.GOLF_R, 94122, 250));
    return watches;
  }

  public static void main(String[] args) {
    List<CarRequestConfig> watches = createWatches();
    HttpUtil httpUtil = new HttpUtil();

    for (CarRequestConfig watch : watches) {
      String carRequestUrl = watch.buildRequestUrl();

      System.out.println("Loading URL: " + carRequestUrl);
      Optional<String> optHtml = httpUtil.loadUrl(carRequestUrl);
      if (!optHtml.isPresent()) {
        System.err.println("Cannot load URL " + carRequestUrl);
        return;
      }

      String html = optHtml.get();
      System.out.println("Parsing loaded HTML: " + html.length());
      int start = html.indexOf(START_TOKEN);
      if (start < 0) {
        System.err.println("Cannot find start token");
        return;
      }

      int end = html.indexOf(END_TOKEN, start);
      if (end < 0) {
        System.err.println("Cannot find end token");
        return;
      }

      String json = html.substring(start + START_TOKEN.length(), end);
      System.out.println(json);

      JSONObject carJson = new JSONObject(json);
      System.out.println("Vehice Count: " + carJson.getInt("vehicleCount"));

      // TODO: Continue here.
    }
  }
}
