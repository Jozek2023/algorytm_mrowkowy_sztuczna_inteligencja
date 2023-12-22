package service;

import data.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private final StringToCityParser stringToCityParser = new StringToCityParser();

    public List<City> readFileAndReturnListOfCities(String fileName) throws IOException {

        Path path = Paths.get(fileName);

        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        ArrayList<City> arrayListOfCities = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            arrayListOfCities.add(stringToCityParser.parseStringToCity(line));
        }
        return arrayListOfCities;
    }

}
