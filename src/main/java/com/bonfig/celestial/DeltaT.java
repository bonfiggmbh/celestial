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

import java.time.OffsetDateTime;

/**
 * DeltaT
 * based on POLYNOMIAL EXPRESSIONS FOR DELTA T of Five Millennium Canon of Solar Eclipses [Espenak and Meeus]
 * http://eclipse.gsfc.nasa.gov/SEcat5/deltatpoly.html
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class DeltaT {

    private final double value;

    public static DeltaT of(final OffsetDateTime t) {
        double y = t.getYear() + (t.getDayOfYear() - 1.0) / t.toLocalDate().lengthOfYear();
        if (y < -500.0) {
            return new DeltaT((y - 1820.0) / 100.0, -20.0, 0.0, 32.0);
        } else if (y < 500.0) {
            return new DeltaT(y / 100.0, 10583.6, -1014.41, 33.78311, -5.952053, -0.1798452, 0.022174192,
                    0.0090316521);
        } else if (y < 1600.0) {
            return new DeltaT((y - 1000.0) / 100.0, 1574.2, -556.01, 71.23472, 0.319781, -0.8503463, -0.005050998,
                    0.0083572073);
        } else if (y < 1700.0) {
            return new DeltaT(y - 1600.0, 120.0, 0.9808, -0.01532, 1.0 / 7129.0);
        } else if (y < 1800.0) {
            return new DeltaT(y - 1700.0, 8.83, 0.1603, -0.0059285, 0.00013336, -1.0 / 1174000.0);
        } else if (y < 1860.0) {
            return new DeltaT(y - 1800.0, 13.72, -0.332447, 0.0068612, 0.0041116, -0.00037436, 0.0000121272,
                    -0.0000001699, 0.000000000875);
        } else if (y < 1900.0) {
            return new DeltaT(y - 1860.0, 7.62, 0.5737, -0.251754, 0.01680668, -0.0004473624, 1.0 / 233174.0);
        } else if (y < 1920.0) {
            return new DeltaT(y - 1900.0, -2.79, 1.494119, -0.0598939, 0.0061966, -0.000197);
        } else if (y < 1941.0) {
            return new DeltaT(y - 1920.0, 21.20, 0.84493, -0.076100, 0.0020936);
        } else if (y < 1961.0) {
            return new DeltaT(y - 1950.0, 29.07, 0.407, -1.0 / 233.0, 1.0 / 2547.0);
        } else if (y < 1986.0) {
            return new DeltaT(y - 1975.0, 45.45, 1.067, -1.0 / 260.0, -1.0 / 718.0);
        } else if (y < 2005.0) {
            return new DeltaT(y - 2000.0, 63.86, 0.3345, -0.060374, 0.0017275, 0.000651814, 0.00002373599);
        } else if (y < 2050.0) {
            return new DeltaT(y - 2000.0, 62.92, 0.32217, 0.005589);
        } else if (y < 2150.0) {
            return new DeltaT(CelestialMath.polynomialInterpolation((y - 1820.0) / 100.0, -20.0, 0.0, 32.0)
                    - 0.5628 * (2150.0 - y));
        } else {
            return new DeltaT((y - 1820.0) / 100.0, -20.0, 0.0, 32.0);
        }
    }

    private DeltaT(final double value) {
        this.value = value;
    }

    private DeltaT(final double x, final double k0, final double... ks) {
        this(CelestialMath.polynomialInterpolation(x, k0, ks));
    }

    public double get() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.4fs", get());
    }

}
