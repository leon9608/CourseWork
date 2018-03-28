package com.company;

import junit.framework.TestCase;


/**
 * Created by Yuyang on 28/2/2017.
 */
public class GraphTest extends TestCase {
    private Testcase example = new Testcase();
    Graph goodGraph;
    Graph badGraph;

    public void setUp() {
        goodGraph = example.GOOD_GRAPH;
        goodGraph.initialize();
        badGraph = example.BAD_GRAPH;
        badGraph.initialize();
    }

    public void testGoodGraph(){
        City starting = goodGraph.getCities()[0];
        City ending = goodGraph.getCities()[1];
        PathFinder solver1 = new PathFinder();
        solver1.setUp(starting, ending, goodGraph);
        String expected = "[Champaign, Bloomington]";
        assertEquals(expected,solver1.solve().toString());

        starting = goodGraph.getCities()[0];
        ending = goodGraph.getCities()[5];
        PathFinder solver2 = new PathFinder();
        solver2.setUp(starting, ending, goodGraph);
        expected = "[Joliet, Bloomington]";
        assertEquals(expected,solver2.solve().toString());
    }

    public void testNoSolution(){
        City starting = badGraph.getCities()[0];
        City ending = badGraph.getCities()[5];
        PathFinder solver1 = new PathFinder();
        solver1.setUp(starting, ending, badGraph);
        assertNull(solver1.solve());
    }
}