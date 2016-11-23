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

import static com.bonfig.celestial.CelestialMath.rad2hrs;

/**
 * LocalSiderealTime
 * based on algorithms by Peter Duffett-Smith's book 'Practical Astronomy with your Calculator'
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class LocalSiderealTime {

    private final GreenwichSiderealTime gst;
    private final double offset;

    public LocalSiderealTime(final GreenwichSiderealTime gst, final double offset) {
        this.gst = gst;
        this.offset = offset;
    }

    public double get() {
        return gst.get() + offset;
    }

    public GreenwichSiderealTime getGst() {
        return gst;
    }

    public double getOffset() {
        return offset;
    }

    public static LocalSiderealTime ofLongitude(GreenwichSiderealTime gst, double longitude) {
        return new LocalSiderealTime(gst, rad2hrs(longitude));
    }

}
