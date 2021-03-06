package com.company;


import java.util.*;

/**
 * A* search algorithm
 *
 * @author Yuyang Liu
 */

public class PathFinder {
    private Node startingPoint;
    private Node endingPoint;

    // closedSet
    private Set<Node> evaluatedSet;
    // openSet
    private List<Node> pendingSet;

    // walkedDistanceMap represents the gscore: the cost of getting from the starting to current.
    private HashMap<Node, Double> walkedDistanceMap;
    // totalDistanceMap represents the fscore:the cost of getting from the starting to goal by passing current
    private HashMap<Node, Double> totalDistanceMap;

    private HashMap<Node, Node> trace;
    private Maps maps;

    /**
     * Initiate all the member variables which will be used to find the shortest path
     *
     * @param start      the starting position
     * @param end        the goal position
     * @param parsedMaps the Map which includes all the necessay data
     */
    public void setUp(Node start, Node end, Maps parsedMaps) {
        maps = parsedMaps;
        startingPoint = start;
        endingPoint = end;
        evaluatedSet = new HashSet<>();
        pendingSet = new ArrayList<>();
        pendingSet.add(startingPoint);
        walkedDistanceMap = new HashMap<>();
        walkedDistanceMap.put(startingPoint, 0.0);
        totalDistanceMap = new HashMap<>();
        totalDistanceMap.put(startingPoint, startingPoint.heuristicEstimate(endingPoint));
        trace = new HashMap<>();
    }

    /**
     * Using the A* search algorithm to find the path that incurs the smallest cost from starting point
     * to the goal in the given maps
     *
     * @return the solution path as a Node ArrayList.
     */
    public ArrayList<Node> solve() {
        while (!pendingSet.isEmpty()) {
            Node current = getShortestPath();
            if (current.equals(endingPoint)) {
                return traceThePath();
            }
            pendingSet.remove(current);
            evaluatedSet.add(current);
            evaluateNeighbour(current);
        }
        return null;
    }

    /**
     * Evaluate the neighbours of the current Node and make changes to pendingSet, totalDistanceMap,
     * walkedDistanceMap accordingly
     *
     * @param current the current position
     */
    private void evaluateNeighbour(Node current) {
        ArrayList<Node> neighbours = maps.getNeighbours(current);

        for (Node neighbour : neighbours) {
            // Ignore the neighbor which is already evaluated.
            if (evaluatedSet.contains(neighbour)) {
                continue;
            }

            // The distance from start to a neighbor
            double tempWalkedDistance = walkedDistanceMap.get(current) + maps.getNeighbourDist(current, neighbour);

            // Discover a new position
            if (!pendingSet.contains(neighbour)) {
                pendingSet.add(neighbour);
            } else if (tempWalkedDistance > walkedDistanceMap.get(neighbour)) {
                continue;
            }
            trace.put(neighbour, current);
            walkedDistanceMap.put(neighbour, tempWalkedDistance);
            totalDistanceMap.put(neighbour, walkedDistanceMap.get(neighbour) + neighbour.heuristicEstimate(endingPoint));
        }
    }

    /**
     * Using the hashmap to trace the shortest distance from the goal to the starting position
     *
     * @return a Node arraylist which contains every node passed by the shortest path
     */
    private ArrayList<Node> traceThePath() {
        ArrayList<Node> output = new ArrayList<>();
        Node current = endingPoint;
        output.add(endingPoint);
        while (trace.containsKey(current)) {
            output.add(trace.get(current));
            current = trace.get(current);
        }
        return output;
    }

    /**
     * Evaluate the total cost of getting from the start to the goal by passing by the specific position
     * in the pending set and find the one with the smallest cost
     *
     * @return the node which most likely provide the shortest path
     */
    private Node getShortestPath() {
        Node shortestPath = pendingSet.get(0);
        for (Node node : pendingSet) {
            if (totalDistanceMap.get(node) < totalDistanceMap.get(shortestPath)) {
                shortestPath = node;
            }
        }
        return shortestPath;
    }
}
