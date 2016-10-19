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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Keeps track and persists which vehicles we have already found in the past so we don't sent duplictaes by email.
 */
class OldVehicleDb {
  private final Set<String> mVins;
  private final File mDbFile;

  static Optional<OldVehicleDb> from(File dbFile) {
    if (dbFile.exists() && (!dbFile.isFile() || !dbFile.canWrite())) {
      System.err.println("Old vehicle db file is either not a file or cannot be written to. " + dbFile.exists());
      return Optional.empty();
    }

    if (!dbFile.exists()) {
      return Optional.of(new OldVehicleDb(new HashSet<>(), dbFile));
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(dbFile))) {
      Set<String> vins = new HashSet<>();
      String vin;
      while ((vin = reader.readLine()) != null) {
        vins.add(vin);
      }
      return Optional.of(new OldVehicleDb(vins, dbFile));
    } catch (FileNotFoundException e) {
      return Optional.of(new OldVehicleDb(new HashSet<>(), dbFile));
    } catch (IOException e) {
      System.err.println("Cannot load old vehicle db file: " + e.getMessage());
      return Optional.empty();
    }
  }

  private OldVehicleDb(Set<String> vins, File dbFile) {
    mVins = vins;
    mDbFile = dbFile;
  }

  /**
   * Adds these vehicles to the DB of old vehicles.
   */
  void addVehicles(List<SearchResponse.Vehicle> vehicle) {
    mVins.addAll(vehicle.stream().map(SearchResponse.Vehicle::getVin).collect(Collectors.toSet()));
  }

  /**
   * Removes all old vehicles from the given list and returns a new list with no old vehicles.
   */
  List<SearchResponse.Vehicle> filterOld(List<SearchResponse.Vehicle> vehicles) {
    return vehicles.stream().filter(v -> !mVins.contains(v.getVin())).collect(Collectors.toList());
  }

  boolean persist() {
    try (FileWriter writer = new FileWriter(mDbFile, false /* append */)) {
      mVins.forEach(vin -> addNewLineToWriter(writer, vin));
      return true;
    } catch (IOException e) {
      System.err.println("Cannot write old vehicle db file");
      return false;
    }
  }

  private static void addNewLineToWriter(FileWriter writer, String line) {
    try {
      writer.append(line).append('\n');
    } catch (IOException e) {
      System.err.println("Cannot write to old vehicle db file: " + e.getMessage());
    }
  }
}
