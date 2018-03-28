package com.company;

import junit.framework.TestCase;


/**
 * Created by Yuyang on 20/2/2017.
 */
public class GridTest extends TestCase {
    private Testcase example = new Testcase();

    public void testNoSolution() {
        // a 3*3 map with obstacles on the diagonals
        Position staringPt = example.NO_SOLUTION_MAP1.getStart();
        Position endingPt = example.NO_SOLUTION_MAP1.getEnd();
        PathFinder solver1 = new PathFinder();
        solver1.setUp(staringPt, endingPt, example.NO_SOLUTION_MAP1);
        assertNull(solver1.solve());

        // a 3*3 map with obstacles surrounds the ending point
        staringPt = example.NO_SOLUTION_MAP2.getStart();
        endingPt = example.NO_SOLUTION_MAP2.getEnd();
        PathFinder solver2 = new PathFinder();
        solver2.setUp(staringPt, endingPt, example.NO_SOLUTION_MAP2);
        assertNull(solver2.solve());

        // a 3*3 map with obstacles separate the map apart
        staringPt = example.NO_SOLUTION_MAP3.getStart();
        endingPt = example.NO_SOLUTION_MAP3.getEnd();
        PathFinder solver3 = new PathFinder();
        solver3.setUp(staringPt, endingPt, example.NO_SOLUTION_MAP3);
        assertNull(solver3.solve());

        // a 100*100 map with obstacles separate the map apart
        staringPt = example.NO_SOLUTION_MAP4.getStart();
        endingPt = example.NO_SOLUTION_MAP4.getEnd();
        PathFinder solver4 = new PathFinder();
        solver4.setUp(staringPt, endingPt, example.NO_SOLUTION_MAP4);
        assertNull(solver4.solve());
    }

    public void testHaveSolution() {
        Position staringPt = example.GOOD_MAP1.getStart();
        Position endingPt = example.GOOD_MAP1.getEnd();
        PathFinder solver1 = new PathFinder();
        solver1.setUp(staringPt, endingPt, example.GOOD_MAP1);
        assertEquals(example.SOLUTION_MAP1, solver1.solve().toString());

        staringPt = example.GOOD_MAP2.getStart();
        endingPt = example.GOOD_MAP2.getEnd();
        PathFinder solver2 = new PathFinder();
        solver2.setUp(staringPt, endingPt, example.GOOD_MAP2);
        assertEquals(example.SOLUTION_MAP2, solver2.solve().toString());

        staringPt = example.GOOD_MAP3.getStart();
        endingPt = example.GOOD_MAP3.getEnd();
        PathFinder solver3 = new PathFinder();
        solver3.setUp(staringPt, endingPt, example.GOOD_MAP3);
        assertEquals(example.SOLUTION_MAP3, solver3.solve().toString());

        staringPt = example.GOOD_MAP4.getStart();
        endingPt = example.GOOD_MAP4.getEnd();
        PathFinder solver4 = new PathFinder();
        solver4.setUp(staringPt, endingPt, example.GOOD_MAP4);
        assertEquals(example.SOLUTION_MAP4, solver4.solve().toString());
    }

    public void testIsValidMap() {
        assertTrue(Main.isValidGrid(example.GOOD_MAP1));

        assertTrue(Main.isValidGrid(example.GOOD_MAP2));

        assertTrue(Main.isValidGrid(example.GOOD_MAP3));

        assertTrue(Main.isValidGrid(example.GOOD_MAP4));

        assertTrue(Main.isValidGrid(example.NO_SOLUTION_MAP1));

        assertTrue(Main.isValidGrid(example.NO_SOLUTION_MAP2));

        assertTrue(Main.isValidGrid(example.NO_SOLUTION_MAP3));

        assertTrue(Main.isValidGrid(example.NO_SOLUTION_MAP4));

        // dimension is a negative double
        assertFalse(Main.isValidGrid(example.INVALID_MAP1));

        // y value of starting point is negative
        assertFalse(Main.isValidGrid(example.INVALID_MAP2));

        // starting and ending point are outside the map
        assertFalse(Main.isValidGrid(example.INVALID_MAP3));

        // starting point is an obstacle
        assertFalse(Main.isValidGrid(example.INVALID_MAP4));

        // the obstacle given is outside the map
        assertFalse(Main.isValidGrid(example.INVALID_MAP5));

        // starting point and ending point are the same
        assertFalse(Main.isValidGrid(example.INVALID_MAP6));
    }

    public void testGetNeighbours() {
        Position position1 = new Position(0, 0);
        assertEquals(2, example.GOOD_MAP1.getNeighbours(position1).size());

        Position position2 = new Position(0, 1);
        assertEquals(3, example.GOOD_MAP1.getNeighbours(position2).size());

        Position position3 = new Position(9, 0);
        assertEquals(2, example.GOOD_MAP1.getNeighbours(position3).size());

        Position position4 = new Position(9, 9);
        assertEquals(2, example.GOOD_MAP1.getNeighbours(position4).size());

        Position position5 = new Position(1, 2);
        assertEquals(3, example.GOOD_MAP1.getNeighbours(position5).size());

        // check neighbour
        assertEquals(position1, example.GOOD_MAP1.getNeighbours(position2).get(1));
    }

}