import java.io.IOException;

/**
 * klasa służy do stworzenia wyników testów
 */
public class AntAlgorithmTest {
    public static void testAntCount() throws IOException {
        int antCount = 38;
        int alpha = 3;
        int beta = -2;
        int pheromoneMin = 15;
        int pheromoneMax = 1000;
        int pheromoneLoosePerRound = 3;
        int pheromoneGainPerRound = 9;
        StringBuilder resultCsvString = new StringBuilder();
        AntAlgorithm antAlgorithm = new AntAlgorithm();
        for (int i = 1; i < 75; i++) {
            double bestPath = antAlgorithm.init(antCount, i, alpha, beta, pheromoneMin, pheromoneMax, pheromoneLoosePerRound, pheromoneGainPerRound);
            resultCsvString.append(i).append(";").append(String.format("%.2f", bestPath)).append("\n");
        }
        System.out.println(resultCsvString);
    }
}