package com.company;



/**
 * Created by Yuyang on 21/2/2017.
 */
public class Testcase {
    final Grid GOOD_MAP1;
    final Grid GOOD_MAP2;
    final Grid GOOD_MAP3;
    final Grid GOOD_MAP4;
    final Grid INVALID_MAP1;
    final Grid INVALID_MAP2;
    final Grid INVALID_MAP3;
    final Grid INVALID_MAP4;
    final Grid INVALID_MAP5;
    final Grid INVALID_MAP6;
    final Grid NO_SOLUTION_MAP1;
    final Grid NO_SOLUTION_MAP2;
    final Grid NO_SOLUTION_MAP3;
    final Grid NO_SOLUTION_MAP4;
    final String SOLUTION_MAP1;
    final String SOLUTION_MAP2;
    final String SOLUTION_MAP3;
    final String SOLUTION_MAP4;
    final Graph GOOD_GRAPH;
    final Graph BAD_GRAPH;

    public Testcase(){
        MagicGson gson = new MagicGson();
        GOOD_MAP1 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\GoodMap1.json");
        GOOD_MAP2 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\GoodMap2.json");
        GOOD_MAP3 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\GoodMap3.json");
        GOOD_MAP4 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\GoodMap4.json");
        INVALID_MAP1 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testInvalidMap1.json");
        INVALID_MAP2 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testInvalidMap2.json");
        INVALID_MAP3 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testInvalidMap3.json");
        INVALID_MAP4 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testInvalidMap4.json");
        INVALID_MAP5 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testInvalidMap5.json");
        INVALID_MAP6 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testInvalidMap6.json");
        NO_SOLUTION_MAP1 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testNoSolutionMap1.json");
        NO_SOLUTION_MAP2 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testNoSolutionMap2.json");
        NO_SOLUTION_MAP3 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testNoSolutionMap3.json");
        NO_SOLUTION_MAP4 = gson.fileToGrid("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\testNoSolutionMap4.json");
        SOLUTION_MAP1 = "[x=9, y=0, x=8, y=0, x=7, y=0, x=6, y=0, x=5, y=0, x=4, y=0, x=3, y=0, x=2, y=0, x=1, y=0, x=0, y=0, x=0, y=1, x=0, y=2, x=0, y=3, x=0, y=4, x=0, y=5, x=0, y=6, x=0, y=7, x=0, y=8, x=0, y=9]";
        SOLUTION_MAP2 = "[x=0, y=0, x=0, y=1, x=0, y=2, x=0, y=3, x=0, y=4, x=0, y=5, x=0, y=6, x=0, y=7, x=0, y=8, x=0, y=9, x=1, y=9, x=2, y=9, x=3, y=9, x=4, y=9, x=5, y=9, x=6, y=9, x=7, y=9, x=8, y=9, x=9, y=9, x=9, y=8, x=9, y=7, x=9, y=6, x=9, y=5, x=9, y=4, x=9, y=3, x=9, y=2, x=9, y=1, x=9, y=0]";
        SOLUTION_MAP3 = "[x=5, y=5, x=4, y=5, x=3, y=5, x=2, y=5, x=1, y=5, x=0, y=5, x=0, y=4, x=0, y=3, x=0, y=2, x=0, y=1, x=0, y=0]";
        SOLUTION_MAP4 = "[x=9, y=9, x=8, y=9, x=7, y=9, x=6, y=9, x=5, y=9, x=4, y=9, x=3, y=9, x=2, y=9, x=1, y=9, x=0, y=9, x=0, y=8, x=0, y=7, x=0, y=6, x=0, y=5, x=0, y=4, x=0, y=3, x=0, y=2, x=0, y=1, x=0, y=0, x=1, y=0, x=2, y=0, x=3, y=0, x=4, y=0, x=5, y=0, x=6, y=0, x=7, y=0, x=8, y=0, x=9, y=0]";
        GOOD_GRAPH = gson.fileToGraph("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\src\\com\\company\\graph.json");
        BAD_GRAPH = gson.fileToGraph("C:\\Users\\Yuyang\\IdeaProjects\\AstarEx\\Test\\com\\company\\BadGraph.json");
    }

}
