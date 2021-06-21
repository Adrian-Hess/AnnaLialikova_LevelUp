package classmodel.water;

import classmodel.base.Consumable;
import classmodel.base.Movable;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class ElectricWaterTransport extends WaterTransport implements Movable, Consumable {
    private boolean isEngineStatic;

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

    ElectricWaterTransport(TransportModels model, String registrationNumber, int electricityConsumption, boolean isEngineStatic,
                           int displacement, TransportTypes type, int capacity, int price) {
        this.setModel(model);
        this.setRegistrationNumber(registrationNumber);
        this.electricityConsumption = electricityConsumption;
        this.isEngineStatic = isEngineStatic;
        this.setDisplacement(displacement);
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
    }
}
