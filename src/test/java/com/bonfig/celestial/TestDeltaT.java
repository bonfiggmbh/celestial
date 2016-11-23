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
 * TestDeltaT
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class TestDeltaT {

    @Test
    public void test0() {
        OffsetDateTime t = OffsetDateTime.of(2004, 12, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        DeltaT dt = DeltaT.of(t);
        assertEquals(64.6723, dt.get(), 0.1);
    }

    @Test
    public void test1() {
        OffsetDateTime t = OffsetDateTime.of(2005, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        DeltaT dt = DeltaT.of(t);
        assertEquals(64.6876, dt.get(), 0.1);
    }

    @Test
    public void test2() {
        OffsetDateTime t = OffsetDateTime.of(2016, 7, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        DeltaT dt = DeltaT.of(t);
        // Poor quality extrapolation
        assertEquals(68.3964, dt.get(), 1.5);
    }

}
