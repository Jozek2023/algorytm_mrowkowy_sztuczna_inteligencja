package service;

import data.DistanceToCity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteProbabilityCounter {

    public DistanceToCity chooseCityToGoToNext(List<DistanceToCity> distanceToCities, int a, int b) {
        double total = 0.0;
        Map<DistanceToCity, Double> rankForRouteToCity = new HashMap<>();

        for (DistanceToCity distanceToCity : distanceToCities) {
            double rank = countRoute(distanceToCity, a, b);
            rankForRouteToCity.put(distanceToCity, rank);
            total += rank;
        }

        double randomTreshold = Math.random();
        double cumulativeProbability = 0.0;

        for (DistanceToCity distanceToCity : distanceToCities) {
            cumulativeProbability += (rankForRouteToCity.get(distanceToCity) / total);
            if (randomTreshold <= cumulativeProbability)
                return distanceToCity;
        }

        throw new IndexOutOfBoundsException("Brak miast do odwiedzenia."); //błąd nie powinien nigdy wystąpić.
    }

    private double countRoute(DistanceToCity d1, int a, int b) {
        return Math.pow(d1.getPheromoneLevel(), a) * Math.pow(d1.getDistanceTo(), b);
    }
}
