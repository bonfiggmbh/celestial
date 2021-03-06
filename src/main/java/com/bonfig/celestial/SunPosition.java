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

import static java.lang.Math.*;

/**
 * SunPosition
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class SunPosition {

    private final EclipticCoordinate eclipticCoordinate;
    private final double distance;
    private final double diameter;

    public static SunPosition of(TerrestrialTime tt) {
        double T = (tt.get() - 2415020.0) / 36525.0; // Julian centuries since 1900 January 0.5
        double T2 = T * T;
        double epsg = Arc.deg2rad(279.6966778 + (36000.76892 + 0.0003025 * T) * T); // Sun's mean ecliptic longitude
        double omeg = Arc.deg2rad(281.2208444 + (1.719175 + 0.000452778 * T) * T); // longitude from Sun at perigee
        double e = 0.01675104 + (-0.0000418 - 0.000000126 * T) * T; // eccentricity from Sun-Earth orbit
        double M = Arc.modrad(epsg - omeg); // Sun's mean anomaly

        // Solve Kepler's equation E - e sin E = M by iteration
        double eps = 1.0e-6;
        double E = M;
        for (; ; ) {
            double del = E - e * sin(E) - M;
            if (abs(del) < eps) {
                break;
            }
            E = E - del / (1.0 - e * cos(E));
        }

        double ny = Arc.modrad(2.0 * atan(sqrt((1 + e) / (1 - e)) * tan(E / 2.0))); // Sun's true anomaly
        double lam = Arc.modrad(ny + omeg); // Sun's ecliptic longitude
        double f = (1.0 + e * cos(ny)) / (1.0 - e * e);
        double r = 149598500.0 / f; // Earth-Sun distance
        double the = Arc.deg2rad(0.533128 * f); // Sun's angular diameter

        return new SunPosition(EclipticCoordinate.of(0.0, lam), r, the);
    }

    private SunPosition(final EclipticCoordinate eclipticCoordinate, final double distance, final double diameter) {
        this.eclipticCoordinate = eclipticCoordinate;
        this.distance = distance;
        this.diameter = diameter;
    }

    public EclipticCoordinate getEclipticCoordinate() {
        return eclipticCoordinate;
    }

    public double getDistance() {
        return distance;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return String.format("%s, %.0fkm, %s", eclipticCoordinate, distance, diameter);
    }

}
