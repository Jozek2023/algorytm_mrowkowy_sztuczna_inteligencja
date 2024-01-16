import data.Ant;
import data.City;
import data.DistanceToCity;
import service.DistancesForAllMatrixGenerator;
import service.FileReader;
import service.RouteProbabilityCounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AntAlgorithm {
    FileReader fileReader = new FileReader();
    DistancesForAllMatrixGenerator distanceCalc = new DistancesForAllMatrixGenerator();
    RouteProbabilityCounter routeProbabilityCounter = new RouteProbabilityCounter();

    public void init(int antCount, int roundCount, int alpha, int beta, int pheromoneMin, int pheromoneMax, int pheromoneLoosePerRound, int pheromoneGainPerRound) throws IOException {
        Random rand = new Random();
        List<City> cities = fileReader.readFileAndReturnListOfCities("miasta.txt");

        List<String> cityNames = cities.stream().map(City::getNameOfCity).toList();

        double bestDistanceTotal = 9999999999999.99;
        var distancesMap = distanceCalc.initDistances(cities, pheromoneMin, pheromoneMax);
        List<String> bestPath = new ArrayList<>();

        for (int i = 0; i < roundCount; i++) {
            List<Ant> antsInThisRound = new ArrayList<>();
            for (int j = 0; j < antCount; j++) {
                String startingCity = cityNames.get(rand.nextInt(cityNames.size()));
                double totalPathDistance = 0.0;
                Ant ant = new Ant(startingCity, j);
                antsInThisRound.add(ant);
                ant.addToVisitedCities(startingCity);
                while (ant.getVisitedCities().size() < cities.size()) {
                    var routesFromCurrentCity = distancesMap.get(ant.getCurrentCity())
                            .stream()
                            .filter(c -> !ant.getVisitedCities().contains(c.getCityDestination()))
                            .toList();
                    var nextCity = routeProbabilityCounter.chooseCityToGoToNext(routesFromCurrentCity, alpha, beta);
                    ant.addToVisitedCities(nextCity.getCityDestination());
                    totalPathDistance += nextCity.getDistanceTo();
                }
                //dodaj miasto startowe jeszcze raz
                totalPathDistance += getDistanceToStartingPointFromLastVisitedCity(ant.getVisitedCities(), distancesMap);
                ant.addToVisitedCities(ant.getVisitedCities().get(0));

                if (totalPathDistance < bestDistanceTotal) {
                    bestDistanceTotal = totalPathDistance;
                    bestPath = ant.getVisitedCities();
                }
            }
            for (Ant ant : antsInThisRound) {
                var visitedCities = ant.getVisitedCities();
                for (int c = 0; c < visitedCities.size() - 1; c++) {
                    var cityTo = visitedCities.get(c + 1);
                    var cityFrom = visitedCities.get(c);
                    distancesMap.get(cityTo).stream().filter(cd -> cd.getCityDestination().equals(cityFrom)).findFirst().ifPresent(d -> d.addToPheromoneLevel(pheromoneGainPerRound));
                    distancesMap.get(cityFrom).stream().filter(cd -> cd.getCityDestination().equals(cityTo)).findFirst().ifPresent(d -> d.addToPheromoneLevel(pheromoneGainPerRound));

                    distancesMap.get(cityTo).stream().filter(cd -> !cd.getCityDestination().equals(cityFrom)).findFirst().ifPresent(d -> d.addToPheromoneLevel(pheromoneLoosePerRound));
                    distancesMap.get(cityFrom).stream().filter(cd -> !cd.getCityDestination().equals(cityTo)).findFirst().ifPresent(d -> d.addToPheromoneLevel(pheromoneLoosePerRound));
                }
            }
            System.out.println("Runda " + i + " zakonczona. Najlepszy dystans po tej rundzie: " + bestDistanceTotal + " a droga to:\n" + bestPath + "\n-------------------------------\n");
        }
    }

    private double getDistanceToStartingPointFromLastVisitedCity(List<String> visitedCities, Map<String, List<DistanceToCity>> distancesMap) {
        return distancesMap.get(visitedCities.get(visitedCities.size() - 1)).get(0).getDistanceTo();
    }
}
