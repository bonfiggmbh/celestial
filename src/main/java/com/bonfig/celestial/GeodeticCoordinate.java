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

import static com.bonfig.celestial.CelestialFormat.*;
import static com.bonfig.celestial.CelestialMath.*;

/**
 * GeodeticCoordinate
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class GeodeticCoordinate {

    private final double latitude;
    private final double longitude;
    private final double elevation;

    public static GeodeticCoordinate of(final double latitude, final double longitude, final double elevation) {
        return new GeodeticCoordinate(latitude, longitude, elevation);
    }

    public static GeodeticCoordinate ofDegrees(final double latitude, final double longitude, final double elevation) {
        return new GeodeticCoordinate(deg2rad(latitude), deg2rad(longitude), elevation);
    }

    private GeodeticCoordinate(final double latitude, final double longitude, final double elevation) {
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
        return String.format("%s, %s, %.0fm",  frad2deg(latitude), frad2deg(longitude), elevation);
    }

    public String toStringAlt() {
        return String.format("%s, %s, %.0fm",  frad2dms(latitude), frad2dms(longitude), elevation);
    }

}
