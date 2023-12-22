import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("miasta.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        ArrayList<City> arrayListOfCities = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            arrayListOfCities.add(parseStringToCity(line));
        }

        System.out.println("Koniec");
    }

    public static City parseStringToCity(String line) {
        String[] splitedLine = line.split(";");
        double x = Double.parseDouble(splitedLine[1]);
        double y = Double.parseDouble(splitedLine[2]) ;
        String nameOfCity = splitedLine[0];
        return new City(x,y,nameOfCity);

        }
    public static distanceBeetwenCity (City )


//    public double calculateDistanceBetweenPointsWithHypot(
//            double x1,
//            double y1,
//            double x2,
//            double y2) {
//
//        double ac = Math.abs(y2 - y1);
//        double cb = Math.abs(x2 - x1);
//
//        return Math.hypot(ac, cb);
    }



