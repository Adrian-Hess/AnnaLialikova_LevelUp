package classmodel.base;

public class Comparator implements java.util.Comparator<PublicTransport> {

    @Override
    public int compare(PublicTransport o1, PublicTransport o2) {
        return Integer.compare(o1.getFuelConsumption(), o2.getFuelConsumption());
    }
}
