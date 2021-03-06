package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TextIO.putln("Grid or Graph?");
        String instruction = TextIO.getln();
        if (instruction.equalsIgnoreCase("Grid")) {
            processGrid(args[0]);
        } else {
            processGraph(args[0]);
        }
    }

    /**
     * To check whether the layout is valid
     *
     * @param layout the layout parsed from gson
     * @return true if it is a valid layout
     */
    public static boolean isValidGrid(Grid layout) {
        try {
            if (layout == null) {
                throw new InvalidMapException("The grid given is wrong.");
            }

            if (layout.getDimension() < 2) {
                throw new InvalidMapException("The grid's dimension is too small.");
            }

            if (!isValidPosition(layout.getStart(), layout)) {
                throw new InvalidMapException("The starting position given is incorrect.");
            }

            if (!isValidPosition(layout.getEnd(), layout)) {
                throw new InvalidMapException("The ending position given is incorrect.");
            }

            if (layout.getStart().equals(layout.getEnd())) {
                throw new InvalidMapException("The ending and starting cannot be the same.");
            }

            for (Position position : layout.getObstacles()) {
                if (!isValidPosition(position, layout)
                        || position.equals(layout.getStart())
                        || position.equals(layout.getEnd())) {
                    throw new InvalidMapException("The obstacle given is incorrect.");
                }
            }
        } catch (InvalidMapException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean isValidPosition(Position position, Grid layout) {
        return position.getX() >= 0
                && position.getX() < layout.getDimension()
                && position.getY() >= 0
                && position.getY() < layout.getDimension();
    }

    private static void processGraph(String arg) {
        Graph cityMap = MagicGson.createGraph(arg);
        cityMap.initialize();
        TextIO.putln("Please enter the starting city.");
        String starting = TextIO.getln();
        int startingIdx = cityMap.getCityIndex(starting);
        TextIO.putln("Please enter the target city.");
        String ending = TextIO.getln();
        int endingIdx = cityMap.getCityIndex(ending);
        PathFinder pathFinder = new PathFinder();
        pathFinder.setUp(cityMap.getCities()[startingIdx], cityMap.getCities()[endingIdx], cityMap);
        ArrayList<Node> solution = pathFinder.solve();
        if (solution != null) {
            for (Node position : solution) {
                System.out.println(position);
            }
        } else {
            System.out.println("There is no solution.");
        }
    }

    private static void processGrid(String arg) {
        Grid grid = MagicGson.createGrid(arg);
        PathFinder pathFinder = new PathFinder();
        pathFinder.setUp(grid.getStart(), grid.getEnd(), grid);
        ArrayList<Node> solution = pathFinder.solve();
        if (solution != null) {
            for (Node position : solution) {
                System.out.println(position);
            }
        } else {
            System.out.println("There is no solution.");
        }
    }

}




