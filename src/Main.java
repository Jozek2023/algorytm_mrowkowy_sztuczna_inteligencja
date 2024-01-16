import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Wypelnij ponizsze wartosci aby uzupelnic parametry algorytmu mrowkowego. Wszystkie wartosci powinny byc liczbami calkowitymi wiekszymi od 0.");
        System.out.print("Podaj liczbe mrowek: ");
        final int antCount = in.nextInt();
        System.out.print("Podaj liczbe rund: ");
        final int roundCount = in.nextInt();
        System.out.print("Podaj wspolczynnik alfa (im wiekszy tym wazniejsze sa feromony): ");
        final int alpha = in.nextInt();
        System.out.print("Podaj wspolczynnik beta (im wiekszy tym wazniejsze sa odleglosci): ");
        final int beta = -in.nextInt();
        System.out.print("Podaj minimalna ilosc feromonu na krawedzi: ");
        final int pheromoneMin = in.nextInt();
        System.out.print("Podaj maksymalna ilosc feromonu na krawedzi: ");
        final int pheromoneMax = in.nextInt();
        System.out.print("Podaj ilosc feromonu usuwana po rundzie: ");
        final int pheromoneLoosePerRound = -in.nextInt();
        System.out.print("Podaj ilosc feromonu dodawana po rundzie: ");
        final int pheromoneGainPerRound = in.nextInt();

        BasicAlgorithm basicAlgorithm = new BasicAlgorithm();
        System.out.println("wyniki dla algorytmu zachlannego: ");
        basicAlgorithm.init();
        System.out.println("-----------------------\n");

        AntAlgorithm antAlgorithm = new AntAlgorithm();
        System.out.println("wyniki dla algorytmu mrowkowego: ");
        antAlgorithm.init(antCount, roundCount, alpha, beta, pheromoneMin, pheromoneMax, pheromoneLoosePerRound, pheromoneGainPerRound);
        System.out.println("Koniec");
    }
}



