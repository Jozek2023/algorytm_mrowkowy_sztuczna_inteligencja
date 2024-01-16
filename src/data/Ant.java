package data;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    private final int id;
    private final List<String> visitedCities = new ArrayList<>();
    private String currentCity;

    public Ant(String currentCity, int id) {
        this.currentCity = currentCity;
        this.id = id;
    }

    public void addToVisitedCities(String cityName) {
        visitedCities.add(cityName);
        currentCity = cityName;
    }

    public List<String> getVisitedCities() {
        return visitedCities;
    }

    public int getId() {
        return id;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
}
