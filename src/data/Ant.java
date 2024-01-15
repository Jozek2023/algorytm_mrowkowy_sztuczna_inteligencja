package data;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    private String currentCity;
    private final int id;

    private final List<String> visitedCities = new ArrayList<>();

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

    public Ant(String currentCity, int id) {
        this.currentCity = currentCity;
        this.id = id;
    }
}
