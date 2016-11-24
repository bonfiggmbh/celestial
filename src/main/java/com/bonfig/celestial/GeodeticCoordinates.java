/*
 * Copyright 2016 Bonfig GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bonfig.celestial;

/**
 * GeodeticCoordinates
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class GeodeticCoordinates {

    private final double latitude;
    private final double longitude;
    private final double elevation;

    public static GeodeticCoordinates of(final double latitude, final double longitude, final double elevation) {
        return new GeodeticCoordinates(latitude, longitude, elevation);
    }

    private GeodeticCoordinates(final double latitude, final double longitude, final double elevation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    @Override
    public String toString() {
        return String.format("WGS84 %.6f°, %.6f°, %.0fm", getLatitude(), getLongitude(), getElevation());
    }

}
