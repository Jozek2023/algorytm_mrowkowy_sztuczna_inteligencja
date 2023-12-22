package data;

public class DistanceToCity implements Comparable<DistanceToCity>{
    private String cityDestination;
    private double distanceTo;

    public String getCityDestination() {
        return cityDestination;
    }

    public double getDistanceTo() {
        return distanceTo;
    }

    public DistanceToCity(String cityDestination, double distanceTo) {
        this.cityDestination = cityDestination;
        this.distanceTo = distanceTo;
    }

    @Override
    public int compareTo(DistanceToCity o) {
        return (int) (this.getDistanceTo() - o.getDistanceTo());
    }
}
