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

import static com.bonfig.celestial.CelestialFormat.frad2deg;
import static com.bonfig.celestial.CelestialMath.hrs2rad;
import static com.bonfig.celestial.CelestialMath.modrad;
import static java.lang.Math.*;

/**
 * HorizonCoordinate
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class HorizonCoordinate {

    private final double azimuth;
    private final double altitude;

    public static HorizonCoordinate of(final EquatorialCoordinate eqc, final GeodeticCoordinate gc,
                                       final LocalSiderealTime lst) {
        final double sinDel = sin(eqc.getDeclination());
        final double cosDel = cos(eqc.getDeclination());
        final double H = modrad(hrs2rad(lst.get()) - eqc.getRightAscension());
        final double sinH = sin(H);
        final double cosH = cos(H);
        final double sinPhi = sin(gc.getLatitude());
        final double cosPhi = cos(gc.getLatitude());
        final double A = modrad(atan2(-cosDel * sinH, sinDel * cosPhi + cosDel * sinPhi * cosH));
        final double a = asin(sinDel * sinPhi + cosDel * cosPhi * cosH);
        return new HorizonCoordinate(A, a);
    }

    private HorizonCoordinate(final double azimuth, final double altitude) {
        this.azimuth = azimuth;
        this.altitude = altitude;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public double getAltitude() {
        return altitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", frad2deg(altitude), frad2deg(altitude));
    }

}
