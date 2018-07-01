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
        int expResult = 184;
        int result = metrics.preservationIndex(temperature, relativeHumidity);
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
        int expResult = 184;
        int result = metrics.preservationIndex(temperature, relativeHumidity);
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
        int expResult = 0;
        int result = metrics.mold(temperature, relativeHumidity);
        assertEquals(expResult, result);
        
        temperature = 9.31;
        relativeHumidity = 100;
        expResult = 3;
        result = metrics.mold(temperature, relativeHumidity);
        assertEquals(expResult, result);
        
        temperature = 44;
        relativeHumidity = 100;
        expResult = 4;
        result = metrics.mold(temperature, relativeHumidity);
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
        int expResult = 0;
        int result = metrics.mold(temperature, relativeHumidity);
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
        float expResult = 11.1F;
        float result = metrics.emc(temperature, relativeHumidity);
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
        float expResult = 11.1F;
        float result = metrics.emc(temperature, relativeHumidity);
        assertEquals(expResult, result, 0.0);      
    }

    /**
     * Test of moldRisk method, of class Metrics.
     */
    @Test
    public void testMoldRisk() {
        System.out.println("moldRisk");
        int moldValue = 0;
        Risk expResult = Risk.GOOD;
        Risk result = metrics.moldRisk(moldValue);
        assertEquals(expResult, result);
        
        moldValue = 3;
        expResult = Risk.RISK;
        result = metrics.moldRisk(moldValue);
        assertEquals(expResult, result);      
    }

    /**
     * Test of naturalAging method, of class Metrics.
     */
    @Test
    public void testNaturalAging() {
        System.out.println("naturalAging");
        int preservationIndex = 10;
        Risk expResult = Risk.RISK;
        Risk result = metrics.naturalAging(preservationIndex);
        assertEquals(expResult, result);
        
        preservationIndex = 60;
        expResult = Risk.OK;
        result = metrics.naturalAging(preservationIndex);
        assertEquals(expResult, result);
        
        preservationIndex = 80;
        expResult = Risk.GOOD;
        result = metrics.naturalAging(preservationIndex);
        assertEquals(expResult, result); 
    }

    /**
     * Test of mechanicalDamage method, of class Metrics.
     */
    @Test
    public void testMechanicalDamage() {
        System.out.println("mechanicalDamage");
        float emc = 15F;
        Risk expResult = Risk.RISK;
        Risk result = metrics.mechanicalDamage(emc);
        assertEquals(expResult, result);
        
        emc = 8F;
        expResult = Risk.OK;
        result = metrics.mechanicalDamage(emc);
        assertEquals(expResult, result);
        
        emc = 3F;
        expResult = Risk.RISK;
        result = metrics.mechanicalDamage(emc);
        assertEquals(expResult, result);
    }

    /**
     * Test of metalCorrosion method, of class Metrics.
     */
    @Test
    public void testMetalCorrosion() {
        System.out.println("metalCorrosion");
        float emc = 5F;
        Risk expResult = Risk.GOOD;
        Risk result = metrics.metalCorrosion(emc);
        assertEquals(expResult, result);
      
        emc = 8F;
        expResult = Risk.OK;
        result = metrics.metalCorrosion(emc);
        assertEquals(expResult, result);
        
        emc = 15F;
        expResult = Risk.RISK;
        result = metrics.metalCorrosion(emc);
        assertEquals(expResult, result);
    }
       
}
