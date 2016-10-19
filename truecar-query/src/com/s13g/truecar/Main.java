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
import com.s13g.truecar.data.SearchResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Main {
  // TOP results are white cars, with four doors from 2015 or newer.
  private static final VehicleFilter whiteFourNew =
      v -> (v.getGenericExteriorColor().toLowerCase().contains("white") ||
          v.getExteriorColor().toLowerCase().contains("white")) &&
          v.getNumDoors() == 4 &&
          v.getYear() >= 2015;
  // TOP result are white.
  private static final VehicleFilter white =
      v -> v.getGenericExteriorColor().toLowerCase().contains("white") ||
          v.getExteriorColor().toLowerCase().contains("white");

  private static List<CarRequestConfig> createWatches() {
    List<CarRequestConfig> watches = new ArrayList<>();
    watches.add(new CarRequestConfig(Maker.AUDI, Model.TT, 2016, 94122, 250, white));
    watches.add(new CarRequestConfig(Maker.AUDI, Model.TTS, 2016, 94122, 250, white));
    watches.add(new CarRequestConfig(Maker.VW, Model.GOLF_R, 2015, 94122, 250, whiteFourNew));
    watches.add(new CarRequestConfig(Maker.VW, Model.GOLF_GTI, 2015, 94122, 250, whiteFourNew));
    return watches;
  }

  public static void main(String[] args) {
    // Use this to mark which vehicles an e-mail has already been sent for.
    Optional<OldVehicleDb> optOldVehicleDb = OldVehicleDb.from(new File("old_vehicles.txt"));
    if (!optOldVehicleDb.isPresent()) {
      return;
    }
    OldVehicleDb oldVehicleDb = optOldVehicleDb.get();

    // Use the mailer to mail out new results. Config data is taken from a config file.
    Optional<Mailer> optMailer = Optional.empty();
    Optional<Properties> optProperties = loadProperties();
    if (!optProperties.isPresent()) {
      System.out.println("Cannot load config.properties file.");
    } else {
      optMailer = Mailer.from(optProperties.get());
    }

    // Get all active watches, see above.
    List<CarRequestConfig> watches = createWatches();
    for (CarRequestConfig watch : watches) {
      List<SearchResponse.Vehicle> vehicles = new CompleteListRequester(watch).fetchAllVehicles();
      System.out.println("Number of vehicles: " + vehicles.size());
      if (optMailer.isPresent()) {
        if (optMailer.get().sendMatches("[TrueCar Alert] " + watch,
            oldVehicleDb.filterOld(vehicles), watch.getTopMatchFilter())) {
          oldVehicleDb.addVehicles(vehicles);
        }
      } else {
        System.out.println("Not sending results via e-mail.");
      }
    }

    // Persist the new list of already sent vehicles.
    optOldVehicleDb.get().persist();
  }

  private static Optional<Properties> loadProperties() {
    File file = new File("config.properties");
    if (!file.exists() || !file.isFile()) {
      return Optional.empty();
    }

    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream(file));
      return Optional.of(properties);
    } catch (IOException ex) {
      return Optional.empty();
    }
  }
}
