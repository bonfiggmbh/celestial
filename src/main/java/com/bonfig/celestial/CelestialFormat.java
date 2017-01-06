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

import static com.bonfig.celestial.CelestialMath.*;

/**
 * CelestialFormat
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
class CelestialFormat {

    static String frad2deg(double rad) {
        double v = rad2deg(rad);
        return String.format("%.6f°", v);
    }

    static String frad2dms(double rad) {
        if (rad < 0) {
            return "-" + frad2dms(-rad);
        }
        double v = rad2deg(rad);
        int h = (int) v;
        v = 60.0 * (v - h);
        int m = (int) v;
        v = 60.0 * (v - m);
        int s = (int) v;
        return String.format("%d°%02d'%02d''", h, m, s);
    }

    static String frad2hrs(double rad) {
        double v = rad2hrs(rad);
        return String.format("%.4fh", v);
    }

    static String frad2hms(double rad) {
        if (rad < 0) {
            return "-" + frad2hms(-rad);
        }
        double v = rad2hrs(rad);
        int h = (int) v;
        v = 60.0 * (v - h);
        int m = (int) v;
        v = 60.0 * (v - m);
        int s = (int) v;
        return String.format("%dh%02dm%02ds", h, m, s);
    }

}
