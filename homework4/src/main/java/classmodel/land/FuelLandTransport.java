package classmodel.land;

import classmodel.base.Consumable;
import classmodel.base.Movable;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class FuelLandTransport extends LandTransport implements Movable, Consumable {
    private int fuelTank;

    public int getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    @Override
    public int Reduction(int fuelConsumption) {
        if (isMoving) {
            fuelTank -= fuelConsumption;
        }
        return fuelTank;
    }

    public FuelLandTransport(TransportModels model, String registrationNumber, int fuelConsumption,
                             int fuelTank, TransportTypes type, int capacity, int price) {
        this.setModel(model);
        this.setRegistrationNumber(registrationNumber);
        this.setFuelConsumption(fuelConsumption);
        this.fuelTank = fuelTank;
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
    }
}
