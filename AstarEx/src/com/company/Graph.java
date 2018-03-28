package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuyang on 27/2/2017.
 */
public class Graph implements Maps<City> {
    private City[] nodes;
    private Edge[] edges;
    private int[][] edgeWeight;
    private Map<String, Integer> nameToNodeIndexMap;

    public void initialize() {
        // create a map from the name of the node to its index
        nameToNodeIndexMap = new HashMap<>();
        for (int CityIdx = 0; CityIdx < nodes.length; CityIdx++) {
            nameToNodeIndexMap.put(nodes[CityIdx].getName(), CityIdx);
        }

        // create and populate the adjacency matrix
        int numNodes = nodes.length;
        edgeWeight = new int[numNodes][numNodes];       // default values are zeroes
        for (Edge edge : edges) {
            int node1 = nameToNodeIndexMap.get(edge.getNode1());
            int node2 = nameToNodeIndexMap.get(edge.getNode2());
            edgeWeight[node1][node2] = edge.getWeight();
            edgeWeight[node2][node1] = edge.getWeight();
        }
    }

    public int getCityIndex(String cityName) {
        cityName = cityName.toLowerCase();
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        if (nameToNodeIndexMap.containsKey(cityName)) {
            return nameToNodeIndexMap.get(cityName);
        }
        return -1;
    }

    /**
     * * Find the cities which can be directly reached by the current city
     *
     * @param current the current city
     * @return the arraylist of cities can be directly reached by the current city
     */
    @Override
    public ArrayList<City> getNeighbours(City current) {
        int row = nameToNodeIndexMap.get(current.getName());
        ArrayList<City> neighbours = new ArrayList<>();
        for (int col = 0; col < edgeWeight[row].length; col++) {
            if (edgeWeight[row][col] > 0) {
                neighbours.add(nodes[col]);
            }
        }
        return neighbours;
    }

    /**
     * Find the distance between two connected cities
     *
     * @param current   the current city
     * @param neighbour the other city connected to the current city
     * @return the int representation of distance between two cities
     */
    @Override
    public int getNeighbourDist(City current, City neighbour) {
        int row = nameToNodeIndexMap.get(current.getName());
        int col = nameToNodeIndexMap.get(neighbour.getName());
        return edgeWeight[row][col];
    }

    public City[] getCities() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }
}
