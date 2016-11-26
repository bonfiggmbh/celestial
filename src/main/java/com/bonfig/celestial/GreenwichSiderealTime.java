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
 * GreenwichSiderealTime
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class GreenwichSiderealTime {

    private final double value;

    public static GreenwichSiderealTime of(final JulianDate jd) {
        double JD = trunc(jd.get() - 0.5) + 0.5;
        double S = JD - 2451545.0;
        double T = S / 36525.0;
        double T0 = 6.697374558 + (2400.051336 + 0.000025862 * T) * T;
        double UT = frac(jd.get() - 0.5) * 24.0 * 1.002737909;
        double GST = floorMod(UT + T0, 24.0);
        return new GreenwichSiderealTime(GST);
    }

    private GreenwichSiderealTime(final double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.4fh", value);
    }

}
