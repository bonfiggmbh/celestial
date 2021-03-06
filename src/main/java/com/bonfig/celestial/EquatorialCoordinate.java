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
 * EquatorialCoordinate
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class EquatorialCoordinate {

    private final Arc rightAscension;
    private final Arc declination;

    public static EquatorialCoordinate of(final double rightAscension, final double declination) {
        if (rightAscension < ZERO || rightAscension >= CIRCLE) {
            throw new IllegalArgumentException("RightAscension must be in range [0, 2*PI[: " + rightAscension);
        }
        if (declination < -QUARTER_CIRCLE || declination > QUARTER_CIRCLE) {
            throw new IllegalArgumentException("Declination must be in range [-PI/2, PI/2]: " + declination);
        }
        return new EquatorialCoordinate(Arc.of(rightAscension), Arc.of(declination));
    }

    public static EquatorialCoordinate ofHoursAndDegrees(final double rightAscension, final double declination) {
        if (rightAscension < ZERO || rightAscension > CIRCLE_HOURS) {
            throw new IllegalArgumentException("RightAscension must be in range [0, 24[: " + rightAscension);
        }
        if (declination < -QUARTER_CIRCLE_DEGREES || declination > QUARTER_CIRCLE_DEGREES) {
            throw new IllegalArgumentException("Declination must be in range [-90, 90]: " + declination);
        }
        return new EquatorialCoordinate(Arc.ofHours(rightAscension), Arc.ofDegrees(declination));
    }

    public static EquatorialCoordinate of(EclipticCoordinate ecc, TerrestrialTime tt) {
        double T = (tt.get() - 2451545.0) / 36525.0; // Julian centuries since 2000 January 1.5
        double eps = deg2rad(23.0 + (26.0 + (21.45 + (-46.815 + (-0.0006 + 0.00181 * T) * T) * T) / 60.0) / 60.0); // mean obliquity from the ecliptic
        double sinEps = sin(eps);
        double cosEps = cos(eps);
        double sinBet = Arc.sin(ecc.getLatitude());
        double cosBet = Arc.cos(ecc.getLatitude());
        double tanBet = sinBet / cosBet;
        double sinLam = Arc.sin(ecc.getLongitude());
        double cosLam = Arc.cos(ecc.getLongitude());

        double alp = modrad(atan2(sinLam * cosEps - tanBet * sinEps, cosLam));
        double del = asin(sinBet * cosEps + cosBet * sinEps * sinLam);
        return of(alp, del);
    }

    private EquatorialCoordinate(final Arc rightAscension, final Arc declination) {
        this.rightAscension = rightAscension;
        this.declination = declination;
    }

    public Arc getRightAscension() {
        return rightAscension;
    }

    public Arc getDeclination() {
        return declination;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", rightAscension, declination);
    }

    public String toStringHoursAndDegrees() {
        return String.format("%s, %s", rightAscension.toStringHours(), declination.toStringDegrees());
    }

}
