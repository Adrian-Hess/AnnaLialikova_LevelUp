package classmodel.water;

import classmodel.base.Consumable;
import classmodel.base.Movable;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class FuelWaterTransport extends WaterTransport implements Consumable, Movable {
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

    FuelWaterTransport(TransportModels model, String registrationNumber, int fuelConsumption, int fuelTank,
                       int displacement, TransportTypes type, int capacity, int price) {
        this.setModel(model);
        this.setRegistrationNumber(registrationNumber);
        this.setFuelConsumption(fuelConsumption);
        this.fuelTank = fuelTank;
        this.setDisplacement(displacement);
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
    }
}
