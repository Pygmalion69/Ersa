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
package eu.sergehelfrich.ersa.preservation;

import eu.sergehelfrich.ersa.Scale;
import eu.sergehelfrich.ersa.Temperature;
import java.io.InputStream;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Preservation Metrics
 *
 * @see
 * <a href="https://www.imagepermanenceinstitute.org/environmental/research/preservation-metrics">Preservation
 * Metrics</a>
 * @see New Tools for Preservation: Assessing Long-Term Environmental Effects on
 * Library and Archives Collections. Published by The Commission on Preservation
 * and Access, 1995.
 * @author helfrich
 */
public class Metrics {

    /**
     * Preservation Index
     */
    private int[] piTable;
    /**
     * Equilibrium Moisture Content
     */
    private float[] emcTable;

    public Metrics() {

        Reader piReader = null;
        Reader emcReader = null;

        try {
            InputStream isPi = getClass().getClassLoader().getResourceAsStream("pi.json");
            InputStream isEmc = getClass().getClassLoader().getResourceAsStream("emc.json");
            Gson gson = new Gson();
            piReader = new InputStreamReader(isPi, "UTF-8");
            piTable = new Gson().fromJson(piReader, int[].class);
            emcReader = new InputStreamReader(isEmc, "UTF-8");
            emcTable = new Gson().fromJson(emcReader, float[].class);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Metrics.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (piReader != null) {
                    piReader.close();
                }
                if (emcReader != null) {
                    emcReader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Metrics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Preservation Index
     *
     * @param temperature deg. Celsius
     * @param relativeHumidity %
     * @return PI
     */
    public int preservationIndex(double temperature, double relativeHumidity) {
        return piTable[((temperature < -23 ? -23 : temperature > 65 ? 65 : (int) Math.round(temperature)) + 23) * 90 + (relativeHumidity < 6l ? 6 : relativeHumidity > 95 ? 95 : (int) Math.round(relativeHumidity)) - 6];
    }

    /**
     * Preservation Index
     *
     * @param temperature
     * @param relativeHumidity %
     * @return PI
     * @see eu.sergehelfrich.ersa.Temperature
     */
    public int preservationIndex(Temperature temperature, double relativeHumidity) {
        temperature.setScale(Scale.CELSIUS);
        return preservationIndex(temperature.getTemperature(), relativeHumidity);
    }

    /**
     * Mold Risk
     *
     * @param temperature deg. C
     * @param relativeHumidity %
     * @return
     */
    public int mold(double temperature, double relativeHumidity) {
        if (temperature > 45 || temperature < 2 || relativeHumidity < 65) {
            return 0;
        }
        return piTable[8010 + ((int) Math.round(temperature) - 2) * 36 + (int) Math.round(relativeHumidity) - 65];
    }

    /**
     * Mold Risk
     *
     * @param temperature
     * @param relativeHumidity %
     * @return
     */
    public int mold(Temperature temperature, double relativeHumidity) {
        temperature.setScale(Scale.CELSIUS);
        return mold(temperature.getTemperature(), relativeHumidity);
    }

    /**
     * Equilibrium Moisture Content
     *
     * @param temperature deg. C
     * @param relativeHumidity %
     * @return
     */
    public float emc(double temperature, double relativeHumidity) {
        return emcTable[(Math.max(-20, Math.min(65, (int) Math.round(temperature))) + 20) * 101 + (int) Math.round(relativeHumidity)];
    }

    /**
     * Equilibrium Moisture Content
     *
     * @param temperature
     * @param relativeHumidity %
     * @return
     */
    public float emc(Temperature temperature, double relativeHumidity) {
        temperature.setScale(Scale.CELSIUS);
        return emc(temperature.getTemperature(), relativeHumidity);
    }

    /**
     * Mold risk
     *
     * @param moldValue
     * @return
     */
    public Risk moldRisk(int moldValue) {
        return moldValue == 0 ? Risk.GOOD : Risk.RISK;
    }

    /**
     * Natural aging (chemical decay)
     *
     * @param preservationIndex
     * @return
     */
    public Risk naturalAging(int preservationIndex) {
        return preservationIndex < 45 ? Risk.RISK : preservationIndex >= 75 ? Risk.GOOD : Risk.OK;
    }

    /**
     * Mechanical damage (definition?)
     *
     * @param emc
     * @return
     */
    public Risk mechanicalDamage(float emc) {
        return emc < 5 || emc > 12.5 ? Risk.RISK : Risk.OK;
    }

    /**
     * Metal corrosion
     *
     * @param emc
     * @return
     */
    public Risk metalCorrosion(float emc) {
        return emc < 7.0 ? Risk.GOOD : emc > 10.5 ? Risk.RISK : Risk.OK;
    }

}
