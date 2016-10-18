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
      return mJsonRoot.getString("make") + " " + mJsonRoot.getString("model");
    }

    public String getVin() {
      return mJsonRoot.getString("vin");
    }

    public String getStockNumber() {
      return mJsonRoot.getString("stockNumber");
    }

    public int getYear() {
      return mJsonRoot.getInt("year");
    }

    public int getNumDoors() {
      return mJsonRoot.getInt("doors");
    }

    public String getDriveDrain() {
      return mJsonRoot.getString("drivetrain");
    }

    public String getExteriorColor() {
      return mJsonRoot.getString("exteriorColor");
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
      return mJsonRoot.getString("transmission");
    }

    public String getZipCode() {
      return mJsonRoot.getString("zipcode");
    }

    public String getListingUrl() {
      return "https://www.truecar.com/used-cars-for-sale/listing/" + getVin();
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("Listing URL   : ").append(getListingUrl()).append('\n');
      result.append("Make + Model  : ").append(getMakeAndModel()).append('\n');
      result.append("VIN #         : ").append(getVin()).append('\n');
      result.append("Stock #       : ").append(getStockNumber()).append('\n');
      result.append("Year          : ").append(getYear()).append('\n');
      result.append("# Doors       : ").append(getNumDoors()).append('\n');
      result.append("ExtColor      : ").append(getExteriorColor()).append('\n');
      result.append("Drive drain   : ").append(getDriveDrain()).append('\n');
      result.append("List price    : ").append(getListPrice()).append('\n');
      result.append("Member price  : ").append(getMemberPrice()).append('\n');
      result.append("Mileage       : ").append(getMileage()).append('\n');
      result.append("Transmission  : ").append(getTransmission()).append('\n');
      result.append("ZIP Code      : ").append(getZipCode());
      return result.toString();
    }
  }
}
