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
 * TerrestrialTime, aka. Terrestrial Dynamical Time
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class TerrestrialTime {

    private final double value;

    public static TerrestrialTime of(final JulianDate jd, final DeltaT deltaT) {
        return new TerrestrialTime(jd.get() + deltaT.get() / 86400.0);
    }

    private TerrestrialTime(final double value) {
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
