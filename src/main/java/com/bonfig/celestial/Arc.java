/*
 * Copyright 2017 Bonfig GmbH
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
 * Arc in radians.
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class Arc {

    static final double PI = Math.PI;
    static final double CIRCLE = 2.0 * PI;
    static final double CIRCLE_DEGREES = 360.0;
    static final double CIRCLE_HOURS = 24.0;
    static final double HALF_CIRCLE = PI;
    static final double HALF_CIRCLE_DEGREES = 180.0;
    static final double HALF_CIRCLE_HOURS = 12.0;
    static final double QUARTER_CIRCLE = PI / 2.0;
    static final double QUARTER_CIRCLE_DEGREES = 90.0;
    static final double QUARTER_CIRCLE_HOURS = 6.0;
    private static final String FORMAT_DEGREES = "%d°%02d'%06.3f\" (%.6f°)";
    private static final String FORMAT_HOURS = "%dh%02dm%06.3fs (%.6fh)";
    protected final double value;

    public static Arc of(double value) {
        return new Arc(value);
    }

    public static Arc ofDegrees(double value) {
        return new Arc(deg2rad(value));
    }

    public static Arc ofHours(double value) {
        return new Arc(hrs2rad(value));
    }

    static double sin(Arc arc) {
        return Math.sin(arc.value);
    }

    static double cos(Arc arc) {
        return Math.cos(arc.value);
    }

    static double tan(Arc arc) {
        return Math.tan(arc.value);
    }

    static double modrad(final double rad) {
        return CelestialMath.floorMod(rad, CIRCLE);
    }

    static double deg2rad(double deg) {
        return deg * CIRCLE / CIRCLE_DEGREES;
    }

    static double rad2deg(double rad) {
        return rad * CIRCLE_DEGREES / CIRCLE;
    }

    static double rad2hrs(double rad) {
        return rad * CIRCLE_HOURS / CIRCLE;
    }

    static double hrs2rad(double hrs) {
        return hrs * CIRCLE / CIRCLE_HOURS;
    }

    private static String formatUmsv(String format, double value) {
        double s = value;
        int u = (int) s;
        s = Math.abs(60.0 * (s - u));
        int m = (int) s;
        s = 60.0 * (s - m);
        return String.format(format, u, m, s, value);
    }

    protected Arc(double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    public double getDegrees() {
        return rad2deg(value);
    }

    public double getHours() {
        return rad2hrs(value);
    }

    @Override
    public String toString() {
        return String.format("%.6f", value);
    }

    public String toStringDegrees() {
        return formatUmsv(FORMAT_DEGREES, getDegrees());
    }

    public String toStringHours() {
        return formatUmsv(FORMAT_HOURS, getHours());
    }

}
