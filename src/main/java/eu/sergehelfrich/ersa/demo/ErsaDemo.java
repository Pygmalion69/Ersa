/*
 * Copyright (C) 2018
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.sergehelfrich.ersa.demo;

import eu.sergehelfrich.ersa.Dew;
import eu.sergehelfrich.ersa.Scale;
import eu.sergehelfrich.ersa.Temperature;
import eu.sergehelfrich.ersa.preservation.Metrics;
import eu.sergehelfrich.ersa.solver.SolverException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author helfrich
 */
public class ErsaDemo extends Application {

    private static final double DEFAULT_TEMPERATURE = 20;

    private final Temperature temperature = new Temperature(DEFAULT_TEMPERATURE, Scale.CELSIUS);

    private final Temperature dewPoint = new Temperature(Scale.CELSIUS);

    private final ToggleGroup scaleGroup = new ToggleGroup();

    private final Slider temperatureSlider = new Slider(temperature.getMIN(),
            temperature.getMAX(), DEFAULT_TEMPERATURE);

    private final Slider humiditySlider = new Slider(0, 100, 50);

    private final Label temperatureValueLabel = new Label();
    private final Label humidityValueLabel = new Label();

    private final Label dewPointLabel = new Label();

    private final Dew dew = new Dew();
    
    private final Metrics metrics = new Metrics();

    private boolean listen = true;

    ChangeListener<Toggle> scaleChangeListener = (ov, old_toggle, new_toggle) -> {

        if (scaleGroup.getSelectedToggle() != null) {
            listen = false;
            synchronized (ErsaDemo.this) {
                temperature.setTemperature(temperatureSlider.getValue());
                Scale scale = (Scale) scaleGroup.getSelectedToggle().getUserData();
                temperature.setScale(scale);
                temperatureSlider.setMin(temperature.getMIN());
                temperatureSlider.setMax(temperature.getMAX());
                temperatureSlider.setValue(oneDecimal(temperature.getTemperature()));
                updateDewPoint();
            }
            listen = true;
        }

    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ersa Demo");
        Pane root = new StackPane();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(12);

        HBox temperatureScaleBox = new HBox();
        temperatureScaleBox.setSpacing(12);
        Label temperatureLabel = new Label("Temperature");

        RadioButton celsiusButton = new RadioButton("℃");
        celsiusButton.setSelected(true);
        celsiusButton.setToggleGroup(scaleGroup);
        celsiusButton.setUserData(Scale.CELSIUS);
        RadioButton fahrenheitButton = new RadioButton("℉");
        fahrenheitButton.setToggleGroup(scaleGroup);
        fahrenheitButton.setUserData(Scale.FAHRENHEIT);
        RadioButton kelvinButton = new RadioButton("K");
        kelvinButton.setToggleGroup(scaleGroup);
        kelvinButton.setUserData(Scale.KELVIN);

        scaleGroup.selectedToggleProperty().addListener(scaleChangeListener);

        temperatureScaleBox.getChildren().addAll(temperatureLabel, celsiusButton,
                fahrenheitButton, kelvinButton);

        HBox temperatureBox = new HBox();

        temperatureSlider.valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if (listen) {
                        temperatureSlider.setValue(oneDecimal(new_val.doubleValue()));
                        temperature.setTemperature(new_val.doubleValue());
                        updateDewPoint();
                    }
                });

        temperatureBox.getChildren().addAll(temperatureSlider, temperatureValueLabel);
        HBox.setHgrow(temperatureSlider, Priority.ALWAYS);

        temperatureValueLabel.setMaxWidth(80);
        temperatureValueLabel.setAlignment(Pos.CENTER);
        temperatureValueLabel.textProperty().bind(temperatureSlider.valueProperty().asString());
        HBox.setHgrow(temperatureValueLabel, Priority.ALWAYS);

        Label humidityLabel = new Label("Relative Humidity (%)");

        HBox humidityBox = new HBox();
        humiditySlider.valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if (listen) {
                        humiditySlider.setValue(oneDecimal(new_val.floatValue()));
                        updateDewPoint();
                    }
                });
        humidityBox.getChildren().addAll(humiditySlider, humidityValueLabel);
        HBox.setHgrow(humiditySlider, Priority.ALWAYS);

        humidityValueLabel.setMaxWidth(80);
        humidityValueLabel.setAlignment(Pos.CENTER);
        humidityValueLabel.textProperty().bind(humiditySlider.valueProperty().asString());
        HBox.setHgrow(humidityValueLabel, Priority.ALWAYS);

        dewPointLabel.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(temperatureScaleBox, temperatureBox, humidityLabel,
                humidityBox, dewPointLabel);

        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 600, 165));
        primaryStage.show();
        updateDewPoint();
    }

    private void updateDewPoint() {
        double kelvin = temperature.getKelvin();
        double humidity = humiditySlider.getValue();
        dewPoint.setScale(temperature.getScale());
        try {
            dewPoint.setKelvin(dew.dewPoint(humidity, kelvin));
            dewPointLabel.setText(String.format("Dew Point : %.1f", dewPoint.getTemperature()));
        } catch (SolverException ex) {
            dewPointLabel.setText("--");
            Logger.getLogger(ErsaDemo.class.getName()).log(Level.WARNING, null, ex);
        } catch (IllegalArgumentException ex) {
            dewPointLabel.setText("Dew Point : below valid range");
            Logger.getLogger(ErsaDemo.class.getName()).log(Level.WARNING, null, ex);

        }
        //System.out.println("Mold:" + Metrics.mold(temperature, humidity));

    }

    private double oneDecimal(double value) {
        return (double) Math.round(value * 10) / 10;
    }
}
