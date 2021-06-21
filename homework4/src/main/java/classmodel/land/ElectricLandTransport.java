package classmodel.land;

import classmodel.base.Consumable;
import classmodel.base.Movable;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class ElectricLandTransport extends LandTransport implements Movable, Consumable {
    private int electricityConsumption;

    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(int electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    @Override
    public int Reduction(int electricityConsumption) {
        int reduction = 0;
        if (isMoving) {
            reduction += electricityConsumption;
        }
        return reduction;
    }

    ElectricLandTransport(TransportModels model, String registrationNumber, int electricityConsumption,
                          TransportTypes type, int capacity, int price) {
        this.setModel(model);
        this.setRegistrationNumber(registrationNumber);
        this.electricityConsumption = electricityConsumption;
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
    }
}
