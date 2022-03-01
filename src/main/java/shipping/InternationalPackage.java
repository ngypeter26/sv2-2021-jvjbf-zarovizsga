package shipping;

import java.util.Comparator;

public class InternationalPackage implements Transportable{
    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;
    private final int price = 1200;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        int kmPrice = 10 * distance;
        return breakable ? 2*price + kmPrice : price + kmPrice;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }



    public int getDistance() {
        return distance;
    }

//    @Override
//    public int compareTo(InternationalPackage o) {
//        return o.getKms();
//    }

//    @Override
//    public int compareTo(Object o) {
//        return ((InternationalPackage) o).getKms();
//    }
}
