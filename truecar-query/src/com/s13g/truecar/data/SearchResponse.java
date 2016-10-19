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

package com.s13g.truecar.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Search response JSON wrapper.
 */
public class SearchResponse {
  private final JSONObject mJsonRoot;

  public SearchResponse(JSONObject jsonRoot) {
    mJsonRoot = jsonRoot;
  }

  public int getNumPages() {
    return (int) Math.ceil(getCount() / ((double) getNumPerPage()));
  }

  public int getCount() {
    return mJsonRoot.getJSONObject("searchMeta").getInt("count");
  }

  public int getNumPerPage() {
    return mJsonRoot.getJSONObject("searchMeta").getInt("perPage");
  }

  /**
   * @return Whether the api status code is OK.
   */
  public boolean checkApiStatus() {
    JSONObject apiStatus = mJsonRoot.getJSONObject("apiStatus");
    if (apiStatus == null) {
      System.err.println("Cannot find apiStatus");
      return false;
    }
    int apiStatusCode = apiStatus.getInt("code");
    if (apiStatusCode != 200) {
      System.err.println("API Status Code: " + apiStatusCode);
      return false;
    }
    return true;
  }

  public List<Vehicle> getVehicles() {
    JSONArray searchVehicles = mJsonRoot.getJSONArray("searchVehicles");
    List<Vehicle> result = new ArrayList<>(searchVehicles.length());
    for (int i = 0; i < searchVehicles.length(); ++i) {
      result.add(new Vehicle(searchVehicles.getJSONObject(i)));
    }
    return result;
  }

  public static class Vehicle {
    private final JSONObject mJsonRoot;

    private Vehicle(JSONObject jsonRoot) {
      mJsonRoot = jsonRoot;
    }

    public String getMakeAndModel() {
      return getJsonString("make") + " " + getJsonString("model");
    }

    public String getVin() {
      return getJsonString("vin");
    }

    public String getStockNumber() {
      return getJsonString("stockNumber");
    }

    public int getYear() {
      return mJsonRoot.getInt("year");
    }

    public int getNumDoors() {
      return mJsonRoot.getInt("doors");
    }

    public String getDriveDrain() {
      return getJsonString("drivetrain");
    }

    public String getExteriorColor() {
      return getJsonString("exteriorColor");
    }

    public String getGenericExteriorColor() {
      return getJsonString("exteriorGenericColor");
    }

    public int getListPrice() {
      return mJsonRoot.getInt("listPrice");
    }

    public int getMemberPrice() {
      return mJsonRoot.getInt("memberPrice");
    }

    public int getMileage() {
      return mJsonRoot.getInt("mileage");
    }

    public String getTransmission() {
      return getJsonString("transmission");
    }

    public String getZipCode() {
      return getJsonString("zipcode");
    }

    public String getListingUrl() {
      return "https://www.truecar.com/used-cars-for-sale/listing/" + getVin();
    }

    private String getJsonString(String key) {
      if (mJsonRoot.isNull(key)) {
        return "null";
      }
      return mJsonRoot.getString(key);
    }

    @Override
    public String toString() {
      return "Listing URL   : " + getListingUrl() + '\n' +
          "Make + Model  : " + getMakeAndModel() + '\n' +
          "VIN #         : " + getVin() + '\n' +
          "Stock #       : " + getStockNumber() + '\n' +
          "Year          : " + getYear() + '\n' +
          "# Doors       : " + getNumDoors() + '\n' +
          "GenExtColor   : " + getGenericExteriorColor() + '\n' +
          "ExtColor      : " + getExteriorColor() + '\n' +
          "Drive drain   : " + getDriveDrain() + '\n' +
          "List price    : " + getListPrice() + '\n' +
          "Member price  : " + getMemberPrice() + '\n' +
          "Mileage       : " + getMileage() + '\n' +
          "Transmission  : " + getTransmission() + '\n' +
          "ZIP Code      : " + getZipCode();
    }
  }
}
