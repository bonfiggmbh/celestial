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
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;

/**
 * TestJulianDate
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class TestJulianDate {

    @Test
    public void test0() {
        OffsetDateTime t = OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        JulianDate jd = JulianDate.of(t);
        assertEquals(2415020.500000, jd.get(), 0.00001);
    }

    @Test
    public void test1() {
        OffsetDateTime t = OffsetDateTime.of(2009, 6, 19, 18, 0, 0, 0, ZoneOffset.UTC);
        JulianDate jd = JulianDate.of(t);
        assertEquals(2455002.250000, jd.get(), 0.000001);
    }

    @Test
    public void test2() {
        OffsetDateTime t = OffsetDateTime.of(2016, 11, 20, 11, 5, 36, 0, ZoneOffset.ofHours(1));
        JulianDate jd = JulianDate.of(t);
        assertEquals(2457712.920556, jd.get(), 0.000001);
    }

}
