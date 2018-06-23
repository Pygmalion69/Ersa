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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author helfrich
 */
public class MetricsTest {

    private static Metrics metrics;
    
    public MetricsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        metrics = new Metrics();
    }
    
    @AfterClass
    public static void tearDownClass() {
        metrics = null;
    }

    /**
     * Test of preservationIndex method, of class Metrics.
     */
    @Test
    public void testPreservationIndex_double_double() {
        System.out.println("preservationIndex");
        double temperature = 6.38;
        double relativeHumidity = 62.84;
        Metrics instance = new Metrics();
        int expResult = 184;
        int result = instance.preservationIndex(temperature, relativeHumidity);
        assertEquals(expResult, result);
    }

    /**
     * Test of preservationIndex method, of class Metrics.
     */
    @Test
    public void testPreservationIndex_Temperature_double() {
        System.out.println("preservationIndex");
        Temperature temperature = new Temperature(6.38, Scale.CELSIUS);
        double relativeHumidity = 62.84;
        Metrics instance = new Metrics();
        int expResult = 184;
        int result = instance.preservationIndex(temperature, relativeHumidity);
        assertEquals(expResult, result);
    }

    /**
     * Test of mold method, of class Metrics.
     */
    @Test
    public void testMold_double_double() {
        System.out.println("mold");
        double temperature = 5.08;
        double relativeHumidity = 62.84;
        Metrics instance = new Metrics();
        int expResult = 0;
        int result = instance.mold(temperature, relativeHumidity);
        assertEquals(expResult, result);
    }

    /**
     * Test of mold method, of class Metrics.
     */
    @Test
    public void testMold_Temperature_double() {
        System.out.println("mold");
        Temperature temperature = new Temperature(5.08, Scale.CELSIUS);
        double relativeHumidity = 62.84;
        Metrics instance = new Metrics();
        int expResult = 0;
        int result = instance.mold(temperature, relativeHumidity);
        assertEquals(expResult, result);
    }

    /**
     * Test of emc method, of class Metrics.
     */
    @Test
    public void testEmc_double_double() {
        System.out.println("emc");
        double temperature = 31.78;
        double relativeHumidity = 62.84;
        Metrics instance = new Metrics();
        float expResult = 11.1F;
        float result = instance.emc(temperature, relativeHumidity);
        assertEquals(expResult, result, 0.0);      
    }

    /**
     * Test of emc method, of class Metrics.
     */
    @Test
    public void testEmc_Temperature_double() {
        System.out.println("emc");
        Temperature temperature = new Temperature(31.78, Scale.CELSIUS);
        double relativeHumidity = 62.84;
        Metrics instance = new Metrics();
        float expResult = 11.1F;
        float result = instance.emc(temperature, relativeHumidity);
        assertEquals(expResult, result, 0.0);      
    }
    
}
