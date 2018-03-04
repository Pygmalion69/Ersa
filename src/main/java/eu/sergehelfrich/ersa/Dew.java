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

import eu.sergehelfrich.ersa.solver.Solver;
import eu.sergehelfrich.ersa.solver.SolverException;
import eu.sergehelfrich.ersa.solver.FunctionCallable;
import java.util.logging.Logger;

/**
 * Dew point calculator. Based on the approach by Wolfgang Kuehn in JavaScript.
 *
 * @see <a href="http://www.decatur.de/javascript/dew/">
 * http://www.decatur.de/javascript/dew/</a>
 *
 * @author helfrich
 */
public class Dew {

    private final Solver solver = new Solver();

    /**
     * Compute the dew point for given relative humidity[%] and temperature[K].
     * @param relativeHumidity relative humidity (%)
     * @param temperature temperature (K)
     * @return dew point (K)
     * @throws eu.sergehelfrich.ersa.solver.SolverException Solver does not converge
     */
    public double dewPoint(double relativeHumidity, double temperature) throws SolverException, IllegalArgumentException {        
        return solver.solve((double x) -> pvs(x), relativeHumidity / 100.0 * pvs(temperature), temperature);
    }

    private double calculate(FunctionCallable functionCallable, double relativeHumidity, double temperature) throws SolverException {

        return solver.solve(functionCallable, relativeHumidity / 100.0 * functionCallable.function(temperature), temperature);

    }

    /**
     * Compute Saturation Vapor Pressure for
     * Temperature.MIN&lt;temperature[K]&lt;Temperature.MAX
     *
     * @param temperature temperature
     * @return saturation vapor pressure
     * @see eu.sergehelfrich.ersa.Temperature
     */
    public double pvs(double temperature) throws IllegalArgumentException {
        if (temperature < Temperature.MIN || temperature > Temperature.MAX) {
            throw new IllegalArgumentException("Temperature out of range!");
        } else if (temperature < Temperature.CELSIUS_OFFSET) {
            return pvsIce(temperature);
        } else {
            return pvsWater(temperature);
        }
    }

    /* Ice saturation vapor pressure coefficients */
    private static final double K0 = -5.8666426e3;
    private static final double K1 = 2.232870244e1;
    private static final double K2 = 1.39387003e-2;
    private static final double K3 = -3.4262402e-5;
    private static final double K4 = 2.7040955e-8;
    private static final double K5 = 6.7063522e-1;

    /**
     * Saturation Vapor Pressure formula for range -100..0 Deg. C. This is taken
     * from ITS-90 Formulations for Vapor Pressure, Frostpoint Temperature,
     * Dewpoint Temperature, and Enhancement Factors in the Range 100 to +100 C
     * by Bob Hardy as published in "The Proceedings of the Third International
     * Symposium on Humidity &amp; Moisture", Teddington, London, England, April
     * 1998
     *
     * @param temperature temperature
     * @return saturation vapor pressure
     * @see
     * <a href="http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.564.407&rep=rep1&type=pdf">
     * ITS-90 Formulations for Vapor Pressure, Frostpoint Temperature, Dewpoint
     * Temperature, and Enhancement Factors in the Range 100 to +100 C</a>
     */
    public double pvsIce(double temperature) {
        double lnP = K0 / temperature + K1 + (K2 + (K3 + (K4 * temperature))
                * temperature) * temperature + K5 * Math.log(temperature);
        return Math.exp(lnP);
    }

    /* Water saturation vapor pressure coefficients */
    private static final double N1 = 0.11670521452767e4;
    private static final double N6 = 0.14915108613530e2;
    private static final double N2 = -0.72421316703206e6;
    private static final double N7 = -0.48232657361591e4;
    private static final double N3 = -0.17073846940092e2;
    private static final double N8 = 0.40511340542057e6;
    private static final double N4 = 0.12020824702470e5;
    private static final double N9 = -0.23855557567849;
    private static final double N5 = -0.32325550322333e7;
    private static final double N10 = 0.65017534844798e3;

    /**
     * Saturation Vapor Pressure formula for range 273..678 K. This is taken
     * from the Release on the IAPWS Industrial Formulation 1997 for the
     * Thermodynamic Properties of Water and Steam by IAPWS (International
     * Association for the Properties of Water and Steam), Erlangen, Germany,
     * September 1997.
     *
     * This is Equation (30) in Section 8.1 "The Saturation-Pressure Equation
     * (Basic Equation)"
     *
     * @param temperature temperature
     * @return saturation vapor pressure
     * @see <a href="http://www.iapws.org/relguide/IF97-Rev.pdf">Revised Release
     * on the IAPWS Industrial Formulation 1997 for the Thermodynamic Properties
     * of Water and Steam </a>
     */
    public double pvsWater(double temperature) {
        double th = temperature + N9 / (temperature - N10);
        double a = (th + N1) * th + N2;
        double b = (N3 * th + N4) * th + N5;
        double c = (N6 * th + N7) * th + N8;

        double p = 2 * c / (-b + Math.sqrt(b * b - 4 * a * c));
        p *= p;
        p *= p;
        return p * 1e6;
    }

}
