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
package eu.sergehelfrich.ersa.solver;

/**
 * Saturation vapor pressure function solver
 *
 * @author helfrich
 */
public class Solver {

    /**
     * Newton's Method to solve f(x)=y for x with an initial guess of x0.
     *
     * @param functionCallable f(x)=y
     * @param y y
     * @param x0 x0
     * @return x x
     * @throws eu.sergehelfrich.ersa.solver.SolverException Solver does not converge
     */
    public double solve(FunctionCallable functionCallable, double y, double x0) throws SolverException, IllegalArgumentException {

        double x = x0;
        double xNew = 100 * x; // just make it big
        double maxCount = 10;
        double count = 0;
        while (true) {
            if (count > maxCount) {
                throw new SolverException("Solver does not converge!");
            }

            double dx = x / 1000.0;
            double z = functionCallable.function(x);
            xNew = x + dx * (y - z) / (functionCallable.function(x + dx) - z);
            if (Math.abs((xNew - x) / xNew) < 0.0001) {
                return xNew;
            }
            x = xNew;
            count++;

        }
    }
}
