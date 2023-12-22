package service;

import data.City;

public class DistanceBetweenCitiesCalculator {

    public double getDistanceBetweenCities(City a, City b) {
        double yDiff = Math.abs(b.getY() - a.getY());
        double xDiff = Math.abs(b.getX() - a.getX());
        return Math.hypot(yDiff, xDiff);
    }
}
