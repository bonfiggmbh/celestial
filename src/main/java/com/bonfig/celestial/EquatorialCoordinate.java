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
import static java.lang.Math.*;

/**
 * EquatorialCoordinate
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class EquatorialCoordinate {

    private final double rightAscension;
    private final double declination;

    public static EquatorialCoordinate of(final double rightAscension, final double declination) {
        return new EquatorialCoordinate(rightAscension, declination);
    }

    public static EquatorialCoordinate of(EclipticCoordinate ecc, TerrestrialTime tt) {
        double T = (tt.get() - 2451545.0) / 36525.0; // Julian centuries since 2000 January 1.5
        double eps = deg2rad(23.0 + (26.0 + (21.45 + (-46.815 + (-0.0006 + 0.00181 * T) * T) * T) / 60.0) / 60.0); // mean obliquity of the ecliptic
        double sinEps = sin(eps);
        double cosEps = cos(eps);
        double sinBet = sin(ecc.getLatitude());
        double cosBet = cos(ecc.getLatitude());
        double tanBet = sinBet / cosBet;
        double sinLam = sin(ecc.getLongitude());
        double cosLam = cos(ecc.getLongitude());

        double alp = modrad(atan2(sinLam * cosEps - tanBet * sinEps, cosLam));
        double del = asin(sinBet * cosEps + cosBet * sinEps * sinLam);
        return new EquatorialCoordinate(alp, del);
    }

    private EquatorialCoordinate(final double rightAscension, final double declination) {
        this.rightAscension = rightAscension;
        this.declination = declination;
    }

    public double getRightAscension() {
        return rightAscension;
    }

    public double getDeclination() {
        return declination;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", frad2hrs(rightAscension), frad2deg(declination));
    }

    public String toStringAlt() {
        return String.format("%s, %s", frad2hms(rightAscension), frad2dms(declination));
    }

}
