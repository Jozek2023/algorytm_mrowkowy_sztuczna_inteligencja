package data;

public class DistanceToCity implements Comparable<DistanceToCity> {
    private final String cityDestination;
    private final double distanceTo;
    private final int pheromoneMin;
    private final int pheromoneMax;
    private int pheromoneLevel;

    public DistanceToCity(String cityDestination, double distanceTo, int pheromoneMin, int pheromoneMax) {
        this.cityDestination = cityDestination;
        this.distanceTo = distanceTo;
        this.pheromoneMin = pheromoneMin;
        this.pheromoneLevel = pheromoneMin;
        this.pheromoneMax = pheromoneMax;
    }

    public void addToPheromoneLevel(int pheromoneLevelAdd) {
        final int changeToBeMade = pheromoneLevel + pheromoneLevelAdd;
        if (changeToBeMade >= pheromoneMin && changeToBeMade <= pheromoneMax) {
            pheromoneLevel = changeToBeMade;
        }
    }

    public double getPheromoneLevel() {
        return pheromoneLevel;
    }

    @Override
    public String toString() {
        return "distance to " + cityDestination + " = " + distanceTo;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public double getDistanceTo() {
        return distanceTo;
    }

    @Override
    public int compareTo(DistanceToCity o) {
        return (int) (this.getDistanceTo() - o.getDistanceTo());
    }
}
