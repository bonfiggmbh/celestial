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

import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

/**
 * TestJulianDate
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class TestJulianDate {

    private static final Object[][] TEST_CASES = {
            {"-4712-01-01T12:00:00Z", 0.0},
            {"1582-10-04T23:59:59.999999999Z", 2299160.5},
            {"1582-10-15T00:00:00Z", 2299160.5},
            {"2000-01-01T12:00:00Z", 2451545.0}
    };

    @Test
    public void test() {
        for (Object[] testCase : TEST_CASES) {
            OffsetDateTime t = OffsetDateTime.parse((String) testCase[0]);
            double expected = (Double) testCase[1];
            double actual = JulianDate.of(t).get();
            assertEquals(expected, actual, 0.00001);
        }
    }

}
