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
import java.time.ZoneOffset;

import static com.bonfig.celestial.CelestialMath.deg2rad;
import static com.bonfig.celestial.CelestialMath.rad2deg;

/**
 * Test
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class Test {

    public static void main(String... args) {
        GeodeticCoordinates geo = GeodeticCoordinates.of(50.0, 10.0, 0.0);
        OffsetDateTime t = OffsetDateTime.of(2016, 11, 21, 22, 12, 50, 0, ZoneOffset.ofHours(1));
        // OffsetDateTime t = OffsetDateTime.of(1988, 7, 27, 0, 0, 0, 0, ZoneOffset.ofHours(0));
        DeltaT deltaT = DeltaT.of(t);
        JulianDate jd = JulianDate.of(t);
        GreenwichSiderealTime gst = GreenwichSiderealTime.of(jd);
        LocalSiderealTime lst = LocalSiderealTime.of(gst, geo);
        TerrestrialTime tt = TerrestrialTime.of(jd, deltaT);
        SunPosition sp = SunPosition.of(tt);

        System.out.printf("Geodetic coordinates      %s%n", geo);
        System.out.printf("Time                      %s%n", t);
        System.out.printf("Delta T                   %s%n", deltaT);
        System.out.printf("Julian date               %s%n", jd);
        System.out.printf("Greenwich sidereal time   %s%n", gst);
        System.out.printf("Local sidereal time       %s%n", lst);
        System.out.printf("Sun's position            %s%n", sp);
    }

}
