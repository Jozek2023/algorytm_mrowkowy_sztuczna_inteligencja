package data;

public class DistanceToCity implements Comparable<DistanceToCity>{
    private final String cityDestination;
    private final double distanceTo;
    private double pheromoneLevel;

    public void addToPheromoneLevel(double pheromoneLevelAdd) {
        this.pheromoneLevel += pheromoneLevelAdd;
    }

    public double getPheromoneLevel() {
        return pheromoneLevel;
    }

    public void setPheromoneLevel(double pheromoneLevel) {
        this.pheromoneLevel = pheromoneLevel;
    }
    @Override
    public String toString() {
        return "distance to " + cityDestination + " = " +distanceTo;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public double getDistanceTo() {
        return distanceTo;
    }

    public DistanceToCity(String cityDestination, double distanceTo) {
        this.cityDestination = cityDestination;
        this.distanceTo = distanceTo;
        this.pheromoneLevel = 0.1;

    }

    public DistanceToCity(String cityDestination, double distanceTo, double pheromoneLevel) {
        this.cityDestination = cityDestination;
        this.distanceTo = distanceTo;
        this.pheromoneLevel = pheromoneLevel;
    }

    @Override
    public int compareTo(DistanceToCity o) {
        return (int) (this.getDistanceTo() - o.getDistanceTo());
    }
}
