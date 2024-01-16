package service;

import data.City;
import data.DistanceToCity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DistancesForAllMatrixGenerator {
    public final DistanceBetweenCitiesCalculator calculator = new DistanceBetweenCitiesCalculator();

    public Map<String, List<DistanceToCity>> initDistances(List<City> cities, int pheromoneMin, int pheromoneMax) {
        Map<String, List<DistanceToCity>> listOfAllDestinationsForCityMap = new LinkedHashMap<>();

        for (int i = 0; i < cities.size(); i++) {
            List<DistanceToCity> distancesToOtherCities = new ArrayList<>();
            for (int j = 0; j < cities.size(); j++) {
                if (i != j) {
                    DistanceToCity distanceFromCityItoCityJ = new DistanceToCity(cities.get(j).getNameOfCity(), calculator.getDistanceBetweenCities(cities.get(i), cities.get(j)), pheromoneMin, pheromoneMax);
                    distancesToOtherCities.add(distanceFromCityItoCityJ);
                }
            }
            listOfAllDestinationsForCityMap.put(cities.get(i).getNameOfCity(), distancesToOtherCities);
        }
        return listOfAllDestinationsForCityMap;
    }
}
