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
package eu.sergehelfrich.ersa;

/**
 *
 * @author helfrich
 */
public class Temperature {

    private static final double FAHRENHEIT_CELSIUS = 9.0d / 5.0;

    /**
     * Celsius offset
     */
    public static final double CELSIUS_OFFSET = 273.15;

    /**
     * Minimum temperature
     */
    public static final int MIN = 173; // K

    /**
     * Maximum temperature
     */
    public static final int MAX = 678; // K

    private double value;
    private Scale scale;

    /**
     *
     * @param value value
     * @param scale scale
     * @see eu.sergehelfrich.ersa.Scale
     */
    public Temperature(double value, Scale scale) {
        this.scale = scale;
        setTemperature(value);
    }
    
    /**
     *
     * @param scale temperature scale
     */
    public Temperature(Scale scale) {
        this.scale = scale;
    }

    Temperature() {
    }

    /**
     *
     * @return minimum temperature
     */
    public int getMIN() {
        double minValue = scaled(MIN);
        if (minValue > 0) {
            return (int) Math.floor(minValue);
        } else {
            return (int) Math.ceil(minValue);
        }
    }

    /**
     *
     * @return maximum temperature
     */
    public int getMAX() {
        double maxValue = scaled(MAX);
        if (maxValue > 0) {
            return (int) Math.floor(maxValue);
        } else {
            return (int) Math.ceil(maxValue);
        }
    }

    /**
     *
     * @return temperature
     */
    public final double getTemperature() {
        return scaled(value);
    }

    /**
     *
     * @param value temperature
     */
    public synchronized final void setTemperature(double value) {
        switch (scale) {
            case CELSIUS:
                this.value = value + CELSIUS_OFFSET;
                break;
            case FAHRENHEIT:
                this.value = (value - 32) / FAHRENHEIT_CELSIUS + CELSIUS_OFFSET;
                break;
            case KELVIN:
                this.value = value;
                break;
            default:
                this.value = value;
        }
    }

    /**
     *
     * @return temperature (K)
     */
    public double getKelvin() {
        return value;
    }

    /**
     *
     * @param value temperature (K)
     */
    public void setKelvin(double value) {
        this.value = value;
    }

    /**
     *
     * @return scale
     * @see eu.sergehelfrich.ersa.Scale
     */
    public Scale getScale() {
        return scale;
    }

    /**
     *
     * @param scale scale
     * @see eu.sergehelfrich.ersa.Scale
     */
    public void setScale(Scale scale) {
        this.scale = scale;
    }

    private synchronized double scaled(double kelvinValue) {
        switch (scale) {
            case CELSIUS:
                return kelvinValue - CELSIUS_OFFSET;
            case FAHRENHEIT:
                return 32 + (kelvinValue - CELSIUS_OFFSET) * FAHRENHEIT_CELSIUS;
            case KELVIN:
                return kelvinValue;
            default:
                return kelvinValue;
        }
    }

}
