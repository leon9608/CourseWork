package com.company;


/**
 * Created by Yuyang on 19/2/2017.
 */
public class Position implements Node<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Find the estimated distance between the current position to goal position
     * using the sum of the two adjacent sides of a right angled triangle
     *
     * @param ending the goal position
     * @return the estimate distance between the current position to goal position
     */
    @Override
    public double heuristicEstimate(Position ending) {
        double distance = Math.abs(this.getX() - ending.getX());
        distance += Math.abs(this.getY() - ending.getY());
        return distance;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Position position = (Position) other;
        return (x == position.x) && (y == position.y);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}
