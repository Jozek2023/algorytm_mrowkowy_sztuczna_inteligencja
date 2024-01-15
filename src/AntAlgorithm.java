import data.Ant;
import data.City;
import data.DistanceToCity;
import service.DistancesForAllMatrixGenerator;
import service.FileReader;
import service.RouteProbabilityCounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AntAlgorithm {
    private final int antCount = 1;
    private final int roundCount = 1;
    private final int alpha = 10;
    private final int beta = -10;
    private final int pheromoneLoosePerRound = 10;
    private final int pheromoneGainPerRound = 10;

    FileReader fileReader = new FileReader();
    DistancesForAllMatrixGenerator distanceCalc = new DistancesForAllMatrixGenerator();
    RouteProbabilityCounter routeProbabilityCounter = new RouteProbabilityCounter();

    public void init() throws IOException {
        List<City> cities = fileReader.readFileAndReturnListOfCities("miasta.txt");

        List<String> cityNames = cities.stream().map(City::getNameOfCity).toList();

        double bestDistanceTotal = 9999999999999.99;
        var distancesMap = distanceCalc.initDistances(cities);
        List<String> bestPath = new ArrayList<>();
        String startingCity = cityNames.get(0);

//        for(int i=0; i<roundCount; i++){
            for(int j=0;j<antCount; j++){
                double totalPathDistance = 0.0;
                Ant ant = new Ant(startingCity, j);
                var visitedCitiesByThisAnt = ant.getVisitedCities();
                visitedCitiesByThisAnt.add(startingCity);
                var unvisitedCitiesByThisAnt = distancesMap.get(startingCity);

                while (visitedCitiesByThisAnt.size() < cities.size()){
                    var nextCity = routeProbabilityCounter.chooseCityToGoToNext(unvisitedCitiesByThisAnt, alpha, beta);
                    unvisitedCitiesByThisAnt.remove(nextCity);
                    visitedCitiesByThisAnt.add(nextCity.getCityDestination());
                    totalPathDistance += nextCity.getDistanceTo();
                }

                totalPathDistance += getDistanceToStartingPointFromLastVisitedCity(visitedCitiesByThisAnt, distancesMap);
                visitedCitiesByThisAnt.add(startingCity);

                if(totalPathDistance < bestDistanceTotal){
                    bestDistanceTotal = totalPathDistance;
                    bestPath = visitedCitiesByThisAnt;
                }
            }




//        }
        //tutaj wywołać algorytm mrówkowy


        System.out.println(bestPath);
        System.out.println(bestDistanceTotal);
    }

    private double getDistanceToStartingPointFromLastVisitedCity(List<String> visitedCities, Map<String, List<DistanceToCity>> distancesMap) {
        return distancesMap.get(visitedCities.get(visitedCities.size() - 1)).get(0).getDistanceTo();
    }
}
