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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 *
 * @author helfrich
 */
public class SolverTest {

    private static Solver solver;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    public SolverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        solver = new Solver();
    }
    
    @AfterClass
    public static void tearDownClass() {
        solver = null;
    }

    /**
     * Test of solve method, of class Solver.
     */
    @Test
    public void testSolve() throws Exception {
        System.out.println("solve");
        double result = solver.solve((double x) -> f(x), 6, 5);
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testSolveConvergesFromNegativeInitialGuess() throws Exception {
        double result = solver.solve(this::f, 6, -5);
        assertEquals(-2, result, 0.001);
    }

    @Test
    public void testSolveFlatFunctionThrowsSolverException() throws Exception {
        expectedException.expect(SolverException.class);
        expectedException.expectMessage("Solver does not converge!");

        solver.solve(x -> 1.0, 2, 1);
    }
    
    double f(double x) {
        return Math.pow(x, 2) + 2;
    }
    
}
