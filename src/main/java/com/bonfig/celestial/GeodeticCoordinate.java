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

import static com.bonfig.celestial.Arc.*;

/**
 * GeodeticCoordinate
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class GeodeticCoordinate {

    private final Arc latitude;
    private final Arc longitude;
    private final Length elevation;

    public static GeodeticCoordinate of(final double latitude, final double longitude, final double elevation) {
        if (latitude < -QUARTER_CIRCLE || latitude > QUARTER_CIRCLE) {
            throw new IllegalArgumentException("Latitude must be in range [-PI/2, PI/2]: " + latitude);
        }
        if (longitude < -HALF_CIRCLE || longitude >= HALF_CIRCLE) {
            throw new IllegalArgumentException("Longitude must be in range [-PI, PI[: " + longitude);
        }
        return new GeodeticCoordinate(Arc.of(latitude), Arc.of(longitude), Length.ofKiloMeters(elevation));
    }

    public static GeodeticCoordinate ofDegrees(final double latitude, final double longitude, final double elevation) {
        if (latitude < -QUARTER_CIRCLE_DEGREES || latitude > QUARTER_CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Latitude must be in range [-90, 90]: " + latitude);
        }
        if (longitude < -HALF_CIRCLE_DEGREES || longitude >= HALF_CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Longitude must be in range [-180, 180[: " + longitude);
        }
        return new GeodeticCoordinate(Arc.ofDegrees(latitude), Arc.ofDegrees(longitude), Length.ofKiloMeters(elevation));
    }

    private GeodeticCoordinate(final Arc latitude, final Arc longitude, final Length elevation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public Arc getLatitude() {
        return latitude;
    }

    public Arc getLongitude() {
        return longitude;
    }

    public Length getElevation() {
        return elevation;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", latitude.toStringDegrees(), longitude.toStringDegrees(), elevation.getKiloMeters());
    }

}
