package classmodel.water;

import classmodel.base.Movable;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class Boat extends WaterTransport implements Movable {
    private String matherial;
    private int numberOfPaddles;

    public String getMatherial() {
        return matherial;
    }

    public void setMatherial(String matherial) {
        this.matherial = matherial;
    }

    public int getNumberOfPaddles() {
        return numberOfPaddles;
    }

    public void setNumberOfPaddles(int numberOfPaddles) {
        this.numberOfPaddles = numberOfPaddles;
    }

    Boat(TransportModels model, String registrationNumber, String matherial, int numberOfPaddles,
         TransportTypes type, int capacity, int price) {
        this.setModel(model);
        this.setRegistrationNumber(registrationNumber);
        this.matherial = matherial;
        this.numberOfPaddles = numberOfPaddles;
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
    }
}
