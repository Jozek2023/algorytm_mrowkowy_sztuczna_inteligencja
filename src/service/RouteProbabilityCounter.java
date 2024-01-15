package service;

import data.DistanceToCity;

import java.util.List;

public class RouteProbabilityCounter {

    public DistanceToCity chooseCityToGoToNext(List<DistanceToCity> distanceToCities, int a, int b){
        return distanceToCities.stream()
                .max((d1, d2) -> Double.compare(countRoute(d1, a, b), countRoute(d2, a, b))).orElse(null);
    }

    private double countRoute(DistanceToCity d1, int a, int b){
        return Math.pow(d1.getPheromoneLevel(), a) * Math.pow(d1.getDistanceTo(), b);
    }
}
