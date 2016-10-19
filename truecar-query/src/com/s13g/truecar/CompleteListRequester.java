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

import com.s13g.truecar.data.SearchResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Makes all the requests necessary to get a complete list of cars for the given query.
 */
class CompleteListRequester {
  private final HttpUtil mHttpUtil;
  private final CarRequestConfig mConfig;

  CompleteListRequester(CarRequestConfig config) {
    mHttpUtil = new HttpUtil();
    mConfig = config;
  }

  List<SearchResponse.Vehicle> fetchAllVehicles() {
    return fetchFromPage(1);
  }

  private List<SearchResponse.Vehicle> fetchFromPage(int page) {
    String carRequestUrl = mConfig.buildRequestUrl(page);

    System.out.println("Loading URL: " + carRequestUrl);
    Optional<String> optHtml = mHttpUtil.loadUrl(carRequestUrl);
    if (!optHtml.isPresent()) {
      System.err.println("Cannot load URL " + carRequestUrl);
      return new ArrayList<>();
    }

    Optional<JSONObject> optCarJson = new HtmlParser(optHtml.get()).parseJson();
    if (!optCarJson.isPresent()) {
      return new ArrayList<>();
    }
    SearchResponse searchResponse = new SearchResponse(optCarJson.get());
    if (!searchResponse.checkApiStatus()) {
      return new ArrayList<>();
    }

    List<SearchResponse.Vehicle> vehicles = searchResponse.getVehicles();
    if (page < searchResponse.getNumPages()) {
      vehicles.addAll(fetchFromPage(page + 1));
    }
    return vehicles;
  }
}
