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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Scanner;

/**
 * DeltaTStudy
 *
 * @author Dipl.-Ing. Robert C. Bonfig
 */
public class DeltaTStudy extends Application {

    @Override
    public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        xAxis.setForceZeroInRange(false);
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Delta T");
        yAxis.setForceZeroInRange(false);
        final LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Delta T over Time");

        XYChart.Series<Number, Number> measured = new XYChart.Series<>();
        measured.setName("Measured");
        WeightedObservedPoints points = new WeightedObservedPoints();
        try (Scanner s = new Scanner(DeltaT.class.getClassLoader().getResourceAsStream("deltat.data"), "UTF-8")) {
            s.useLocale(Locale.ENGLISH);
            while (s.hasNext()) {
                int year = s.nextInt();
                int month = s.nextInt();
                int day = s.nextInt();
                double deltaT = s.nextDouble();
                LocalDate date = LocalDate.of(year, month, day);
                double time = date.getYear() + (date.getDayOfYear() - 1.0) / date.lengthOfYear();
                if (year >= 1986 && year <= 2005) {
                    points.add(time - 2000.0, deltaT);
                }
                if (date.getMonthValue() != 1) {
                    continue;
                }

                measured.getData().add(new XYChart.Data<>(time, deltaT));
            }
        }
        chart.getData().add(measured);

        PolynomialFunction f = new PolynomialFunction(PolynomialCurveFitter.create(5).fit(points.toList()));
        System.out.println(f);

        XYChart.Series<Number, Number> interpolated1 = new XYChart.Series<>();
        interpolated1.setName("Interpolated 1");
        XYChart.Series<Number, Number> interpolated2 = new XYChart.Series<>();
        interpolated2.setName("Interpolated 2");
        for (int year = 1986; year < 2005; year++) {
            LocalDate date = LocalDate.of(year, 1, 1);
            double time = date.getYear() + (date.getDayOfYear() - 1.0) / date.lengthOfYear();
            interpolated1.getData().add(new XYChart.Data<>(time, DeltaT.of(OffsetDateTime.of(date, LocalTime.MIDNIGHT, ZoneOffset.UTC)).get()));
            interpolated2.getData().add(new XYChart.Data<>(time, f.value(time - 2000.0)));
        }
        chart.getData().add(interpolated1);
        chart.getData().add(interpolated2);

        Scene scene = new Scene(chart, 800, 600);

        stage.setTitle("Delta T");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
