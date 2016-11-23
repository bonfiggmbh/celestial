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
        double longitude = deg2rad(10.0);
        double latitude = deg2rad(50.0);
        double height = 0.0;
        OffsetDateTime t = OffsetDateTime.of(2016, 11, 21, 22, 12, 50, 0, ZoneOffset.ofHours(1));
        DeltaT dt = DeltaT.of(t);
        JulianDate jd = JulianDate.of(t);
        GreenwichSiderealTime gst = GreenwichSiderealTime.of(jd);
        LocalSiderealTime lst = LocalSiderealTime.ofLongitude(gst, longitude);

        System.out.printf("Longitude                 %.1f°%n", rad2deg(longitude));
        System.out.printf("Latidue                   %.1f°%n", rad2deg(latitude));
        System.out.printf("Time                      %s%n", t);
        System.out.printf("Delta T                   %.1fs%n", dt.get());
        System.out.printf("Julian date               %.5fd%n", jd.get());
        System.out.printf("Greenwich sidereal time   %.5fh%n", gst.get());
        System.out.printf("Local sidereal time       %.5fh%n", lst.get());
    }

}
