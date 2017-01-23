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

import static com.bonfig.celestial.CelestialMath.*;

/**
 * GreenwichSiderealTime in radians.
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class GreenwichSiderealTime extends Arc {

    public static GreenwichSiderealTime of(final JulianDate jd) {
        double JD = trunc(jd.get() - 0.5) + 0.5;
        double T = (JD - 2451545.0) / 36525.0;
        double T0 = polynomialInterpolation(T, 24110.54841, 8640184.812866, 0.093104, 0.0000062) / 3600.0;
        double UT = frac(jd.get() - 0.5) * 24.0 * 1.00273790935;
        double GST = floorMod(T0 + UT, 24.0);
        return new GreenwichSiderealTime(hrs2rad(GST));
    }

    private GreenwichSiderealTime(final double value) {
        super(value);
    }

    @Override
    public String toString() {
        return toStringHours();
    }

}
