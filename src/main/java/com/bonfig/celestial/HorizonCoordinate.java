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
import static java.lang.Math.*;

/**
 * HorizonCoordinate, azimuth and altitude in radians.
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class HorizonCoordinate {

    private final Arc azimuth;
    private final Arc altitude;

    public static HorizonCoordinate of(final double azimuth, final double altitude) {
        if (azimuth < ZERO || azimuth >= CIRCLE) {
            throw new IllegalArgumentException("Azimuth must be in range [0, 2*PI[: " + azimuth);
        }
        if (altitude < -QUARTER_CIRCLE || altitude > QUARTER_CIRCLE) {
            throw new IllegalArgumentException("Altitude must be in range [-PI/2, PI/2]: " + altitude);
        }
        return new HorizonCoordinate(Arc.of(azimuth), Arc.of(altitude));
    }

    public static HorizonCoordinate ofDegrees(final double azimuth, final double altitude) {
        if (azimuth < ZERO || azimuth >= CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Azimuth must be in range [0, 360[: " + azimuth);
        }
        if (altitude < -QUARTER_CIRCLE_DEGREES || altitude > QUARTER_CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Altitude must be in range [-90, 90]: " + altitude);
        }
        return new HorizonCoordinate(Arc.of(azimuth), Arc.of(altitude));
    }

    public static HorizonCoordinate from(final EquatorialCoordinate eqc, final GeodeticCoordinate gc,
                                         final LocalSiderealTime lst) {
        final double sinDel = Arc.sin(eqc.getDeclination());
        final double cosDel = Arc.cos(eqc.getDeclination());
        final double H = modrad(lst.get() - eqc.getRightAscension().get());
        final double sinH = sin(H);
        final double cosH = cos(H);
        final double sinPhi = Arc.sin(gc.getLatitude());
        final double cosPhi = Arc.cos(gc.getLatitude());
        final double A = modrad(atan2(-cosDel * sinH, sinDel * cosPhi + cosDel * sinPhi * cosH));
        final double a = asin(sinDel * sinPhi + cosDel * cosPhi * cosH);
        return of(A, a);
    }

    private HorizonCoordinate(final Arc azimuth, final Arc altitude) {
        this.azimuth = azimuth;
        this.altitude = altitude;
    }

    public Arc getAzimuth() {
        return azimuth;
    }

    public Arc getAltitude() {
        return altitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", azimuth, altitude);
    }

    public String toStringDegrees() {
        return String.format("%s, %s", azimuth.toStringDegrees(), altitude.toStringDegrees());
    }

}
