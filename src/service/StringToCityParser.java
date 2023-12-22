package service;

import data.City;

public class StringToCityParser {
    public City parseStringToCity(String line) {
        String[] splitedLine = line.split(";");
        double x = Double.parseDouble(splitedLine[1]);
        double y = Double.parseDouble(splitedLine[2]) ;
        String nameOfCity = splitedLine[0];
        return new City(x,y,nameOfCity);
    }
}
