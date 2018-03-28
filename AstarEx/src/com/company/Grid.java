package com.company;

import java.util.ArrayList;

/**
 * Created by Yuyang on 19/2/2017.
 */
public class Grid implements Maps<Position> {
    private int dimension;
    private Position start;
    private Position end;
    private ArrayList<Position> obstacles;

    public int getDimension() {
        return dimension;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public ArrayList<Position> getObstacles() {
        return obstacles;
    }

    @Override
    public int getNeighbourDist(Position current, Position neighbour) {
        return 1;
    }

    /**
     * Find the neighbours of the current position by chceking the north, south, west and east.
     * The neighbour which is not an obstacle and inside the map will be added to the arraylist.
     *
     * @param current the current position
     * @return the arraylist of curent position's valid neighbour
     */
    @Override
    public ArrayList<Position> getNeighbours(Position current) {
        ArrayList<Position> neighbours = new ArrayList<Position>();
        int x = current.getX();
        int y = current.getY();
        if ((x > 0) && (!isObstacle(x - 1, y))) {
            neighbours.add(new Position(x - 1, y));
        }
        if ((x < dimension - 1) && (!isObstacle(x + 1, y))) {
            neighbours.add(new Position(x + 1, y));
        }
        if ((y > 0) && (!isObstacle(x, y - 1))) {
            neighbours.add(new Position(x, y - 1));
        }
        if ((y < dimension - 1) && (!isObstacle(x, y + 1))) {
            neighbours.add(new Position(x, y + 1));
        }
        return neighbours;
    }

    public boolean isObstacle(int x, int y) {
        for (Position position : obstacles) {
            if (position.getX() == x && position.getY() == y) {
                return true;
            }
        }
        return false;
    }


}
