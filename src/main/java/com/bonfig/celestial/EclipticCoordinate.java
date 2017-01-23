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
import static com.bonfig.celestial.CelestialMath.ZERO;

/**
 * EclipticCoordinate
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class EclipticCoordinate {

    private final Arc latitude;
    private final Arc longitude;

    public static EclipticCoordinate of(final double latitude, final double longitude) {
        if (latitude < -QUARTER_CIRCLE || latitude > QUARTER_CIRCLE) {
            throw new IllegalArgumentException("Latitude must be in range [-PI/2, PI/2]: " + latitude);
        }
        if (longitude < ZERO || longitude >= CIRCLE) {
            throw new IllegalArgumentException("Longitude must be in range [0, 2*PI[: " + longitude);
        }
        return new EclipticCoordinate(Arc.of(latitude), Arc.of(longitude));
    }

    public static EclipticCoordinate ofDegrees(final double latitude, final double longitude) {
        if (latitude < -QUARTER_CIRCLE_DEGREES || latitude > QUARTER_CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Latitude must be in range [-90, 90]: " + latitude);
        }
        if (longitude < ZERO || longitude >= CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Longitude must be in range [0, 360[: " + longitude);
        }
        return new EclipticCoordinate(Arc.ofDegrees(latitude), Arc.ofDegrees(longitude));
    }

    private EclipticCoordinate(final Arc latitude, final Arc longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Arc getLatitude() {
        return latitude;
    }

    public Arc getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", latitude, longitude);
    }

    public String toStringDegrees() {
        return String.format("%s, %s", latitude.toStringDegrees(), longitude.toStringDegrees());
    }

}
