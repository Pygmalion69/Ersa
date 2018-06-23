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

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author helfrich
 */
@RunWith(Parameterized.class)
public class DewTest {

    private static Dew dew;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {273.15, 50, 264.99}, {283.15, 50, 273.21}
        });
    }

    private final double temperature;
    private final double relativeHumidity;
    private final double expectedDewPoint;

    public DewTest(double temperature, double relativeHumidity, double dewPoint) {
        this.temperature = temperature;
        this.relativeHumidity = relativeHumidity;
        this.expectedDewPoint = dewPoint;
    }

    @BeforeClass
    public static void setUpClass() {
        dew = new Dew();
    }

    @AfterClass
    public static void tearDownClass() {
        dew = null;
    }

    /**
     * Test of expectedDewPoint method, of class Dew.
     */
    @Test
    public void testDewPoint() throws Exception {
        System.out.println("dewPoint");
        assertEquals(expectedDewPoint, dew.dewPoint(relativeHumidity, temperature), .05);
    }

    /**
     * Test of pvs method, of class Dew.
     */
    @Test
    public void testPvs() {
        System.out.println("pvs");
        assertEquals(611, dew.pvs(273.15), 5);
    }

    /**
     * Test of pvsIce method, of class Dew.
     */
    @Test
    public void testPvsIce() {
        System.out.println("pvsIce");
        assertEquals(260, dew.pvsIce(263.15), 5);
    }

    /**
     * Test of pvsWater method, of class Dew.
     */
    @Test
    public void testPvsWater() {
        System.out.println("pvsWater");
        assertEquals(1228, dew.pvsWater(283.15), 5);
        assertEquals(2339, dew.pvsWater(293.15), 5);
    }

}
