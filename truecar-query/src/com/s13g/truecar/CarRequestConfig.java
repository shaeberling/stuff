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

/**
 * Represents a configuration for a request for a used car.
 */
class CarRequestConfig {
  enum Maker {
    AUDI("audi"), VW("volkswagen");

    String mRequestString;

    Maker(String requestString) {
      mRequestString = requestString;
    }

    @Override
    public String toString() {
      return mRequestString;
    }
  }

  enum Model {
    GOLF("golf"), GOLF_R("golf-r"), GOLF_GTI("golf-gti");

    String mRequestString;

    Model(String requestString) {
      mRequestString = requestString;
    }

    @Override
    public String toString() {
      return mRequestString;
    }
  }

  private static final String URL_PATTERN =
      "https://www.truecar.com/used-cars-for-sale/listings/" +
          "%s/%s/location-%s/year-%s-max?used_opt=usedmake&searchRadius=%s&page=%s";

  private final Maker mMaker;
  private final Model mModel;
  private final int mMinYear;
  private final int mZipCode;
  private final int mRadiusMiles;

  CarRequestConfig(Maker maker, Model model, int minYear, int zipCode, int radiusMiles) {
    mMaker = maker;
    mModel = model;
    mMinYear = minYear;
    mZipCode = zipCode;
    mRadiusMiles = radiusMiles;
  }

  /**
   * Builds the request URL.
   */
  String buildRequestUrl(int page) {
    return String.format(URL_PATTERN, mMaker, mModel, mZipCode, mMinYear, mRadiusMiles, page);
  }

  @Override
  public String toString() {
    return mMaker + " " + mModel + " " + mMinYear;
  }
}
