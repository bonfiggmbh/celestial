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

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static com.bonfig.celestial.CelestialMath.trunc;

/**
 * JulianDate
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class JulianDate {

    private static final LocalDate GREGORIAN_CALENDAR_CUTOVER_DATE = LocalDate.of(1582, 10, 15);

    private final double value;

    public JulianDate(final double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    public static JulianDate of(final OffsetDateTime t) {
        double y = t.getYear();
        double m = t.getMonthValue();
        double d = t.getDayOfMonth() + (t.toOffsetTime().toLocalTime().toSecondOfDay() - t.toOffsetTime().getOffset().getTotalSeconds()) / 86400.0;
        if (m < 3) {
            y -= 1;
            m += 12;
        }

        double B = 0.0;
        if (!t.toLocalDate().isBefore(GREGORIAN_CALENDAR_CUTOVER_DATE)) {
            double A = trunc(y / 100.0);
            B = 2.0 - A + trunc(A / 4.0);
        }

        double C = trunc(365.25 * y);
        if (y < 0.0) {
            C -= 0.75;
        }

        double D = trunc(30.6001 * (m + 1));

        double JD = B + C + D + d + 1720994.5;
        return new JulianDate(JD);
    }


}
