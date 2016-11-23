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

/**
 * CelestialMath
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
class CelestialMath {

    static double trunc(final double d) {
        return d < 0.0 ? Math.ceil(d) : Math.floor(d);
    }

    static double frac(final double d) {
        return d - trunc(d);
    }

    static double floorDiv(final double x, final double y) {
        return Math.floor(x / y);
    }

    static double floorMod(final double x, final double y) {
        return x - floorDiv(x, y) * y;
    }

    static double deg2rad(double deg) {
        return deg / 180.0 * Math.PI;
    }

    static double rad2deg(double rad) {
        return rad / Math.PI * 180.0;
    }

    static double rad2hrs(double rad) {
        return rad / Math.PI * 12.0;
    }

    static double polynomialInterpolation(final double x, final double k0, final double... ks) {
        double f = k0;
        double u = x;
        for (double k : ks) {
            f += k * u;
            u *= x;
        }
        return f;
    }

}
