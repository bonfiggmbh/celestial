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
 * LocalSiderealTime in radians.
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class LocalSiderealTime extends Arc {

    private final GreenwichSiderealTime gst;
    private final Arc longitude;

    public static LocalSiderealTime of(GreenwichSiderealTime gst, GeodeticCoordinate geo) {
        return new LocalSiderealTime(gst, geo.getLongitude());
    }

    private LocalSiderealTime(final GreenwichSiderealTime gst, final Arc longitude) {
        super(gst.get() + longitude.get());
        this.gst = gst;
        this.longitude = longitude;
    }

    public GreenwichSiderealTime getGst() {
        return gst;
    }

    public Arc getLongitude() {
        return longitude;
    }

}
