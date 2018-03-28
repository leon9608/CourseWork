package com.company;

/**
 * Created by Yuyang on 27/2/2017.
 */
public class City implements Node<City> {
    private String name;
    private double latitude;
    private double longitude;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        City city = (City) other;

        return (Double.compare(city.latitude, latitude) != 0) &&
                (Double.compare(city.longitude, longitude) != 0) &&
                (name.equals(city.name));
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public double heuristicEstimate(City ending) {
        double distance = Math.abs(latitude - ending.latitude);
        distance += Math.abs(longitude - ending.longitude);
        return distance;
    }
}