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
import java.time.ZoneOffset;

import static com.bonfig.celestial.CelestialMath.trunc;

/**
 * JulianDate
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class JulianDate {

    private static final LocalDate JULIAN_CALENDAR_CUT_OVER_DATE = LocalDate.of(1582, 10, 4);
    private static final LocalDate GREGORIAN_CALENDAR_CUT_OVER_DATE = LocalDate.of(1582, 10, 15);
    private final double value;

    public static JulianDate of(final OffsetDateTime time) {
        OffsetDateTime t = time.withOffsetSameInstant(ZoneOffset.UTC);
        double Y = t.getYear();
        double M = t.getMonthValue();
        double D = t.getDayOfMonth() + (double) t.toLocalTime().toNanoOfDay() / 86_400_000_000_000.0;
        if (M < 3) {
            Y -= 1;
            M += 12;
        }
        double B = 0.0;
        if (!t.toLocalDate().isBefore(GREGORIAN_CALENDAR_CUT_OVER_DATE)) {
            double A = trunc(Y / 100.0);
            B = 2.0 - A + trunc(A / 4.0);
        } else if (t.toLocalDate().isAfter(JULIAN_CALENDAR_CUT_OVER_DATE)) {
            throw new IllegalArgumentException("Invalid date: " + t.toLocalDate());
        }
        double JD = trunc(365.25 * (Y + 4716.0)) + trunc(30.6001 * (M + 1)) + D + B - 1524.5;
        return new JulianDate(JD);
    }

    private JulianDate(final double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.5fd", get());
    }

}
