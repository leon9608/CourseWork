package com.company;

/**
 * Created by Yuyang on 27/2/2017.
 */
public interface Node<Node> {
    double heuristicEstimate(Node ending);
    boolean equals(Object other);
    int hashCode();
}
