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

import static com.bonfig.celestial.CelestialMath.ZERO;

/**
 * Length in AU.
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class Length {

    static final double KM_PER_AU = 149_597_870.7;
    protected final double value;

    public static Length of(double value) {
        if (value < ZERO) {
            throw new IllegalArgumentException("Length must be non-negative: " + value);
        }
        return new Length(value);
    }

    public static Length ofKiloMeters(double value) {
        if (value < ZERO) {
            throw new IllegalArgumentException("Length must be non-negative: " + value);
        }
        return new Length(km2au(value));
    }

    static double km2au(double km) {
        return km / KM_PER_AU;
    }

    static double au2km(double au) {
        return au * KM_PER_AU;
    }

    protected Length(double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    public double getKiloMeters() {
        return au2km(value);
    }

    @Override
    public String toString() {
        return String.format("%.6f", value);
    }

    public String toStringKiloMeters() {
        return String.format("%.1fkm", getKiloMeters());
    }

}
