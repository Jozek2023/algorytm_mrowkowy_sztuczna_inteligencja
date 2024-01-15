import data.City;
import data.DistanceToCity;
import service.DistancesForAllMatrixGenerator;
import service.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicAlgorithm {
    FileReader fileReader = new FileReader();
    DistancesForAllMatrixGenerator distanceCalc = new DistancesForAllMatrixGenerator();

    public void init() throws IOException {
        List<City> cities = fileReader.readFileAndReturnListOfCities("miasta.txt");

        List<String> cityNames = cities.stream().map(City::getNameOfCity).toList();
        List<String> visitedCities = new ArrayList<>();

        double totalPathDistance = 0.0;
        var distancesMap = distanceCalc.initDistances(cities);
        String currentCity = cityNames.get(0);

        visitedCities.add(cityNames.get(0)); // start from first city
        while (visitedCities.size() != cities.size()){
            List<DistanceToCity> sortedDistancesFromCity = getDistancesFromCitySorted(distancesMap.get(currentCity));

            DistanceToCity cityToGoNow = getClosestUnvisitedDistanceToCity(visitedCities, sortedDistancesFromCity);

            totalPathDistance += cityToGoNow.getDistanceTo();
            currentCity = cityToGoNow.getCityDestination();
            visitedCities.add(currentCity);
        }
        totalPathDistance += getDistanceToStartingPointFromLastVisitedCity(visitedCities, distancesMap);
        visitedCities.add(cityNames.get(0));

        System.out.println(visitedCities);
        System.out.println(totalPathDistance);
    }

    private double getDistanceToStartingPointFromLastVisitedCity(List<String> visitedCities, Map<String, List<DistanceToCity>> distancesMap) {
        return distancesMap.get(visitedCities.get(visitedCities.size() - 1)).get(0).getDistanceTo();
    }

    private DistanceToCity getClosestUnvisitedDistanceToCity(List<String> visitedCities, List<DistanceToCity> sortedDistancesFromCity) {
        for (DistanceToCity distanceToCity : sortedDistancesFromCity) {
            if (!visitedCities.contains(distanceToCity.getCityDestination())) {
                return distanceToCity;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public List<DistanceToCity> getDistancesFromCitySorted(List<DistanceToCity> distanceToCities){
        return distanceToCities.stream().sorted().toList();
    }
}
