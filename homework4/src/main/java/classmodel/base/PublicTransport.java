package classmodel.base;

import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class PublicTransport implements Movable, Printable {
    private TransportModels model;
    private String registrationNumber;
    private TransportTypes type;
    private int capacity;
    private int speed;
    private int maxSpeed;
    protected boolean isMoving;
    private int price;
    private int fuelConsumption;

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public TransportTypes getType() {
        return type;
    }

    public void setType(TransportTypes type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    @Override
    public void move() {
        if (isMoving == true) {
            if (speed < maxSpeed) {
                speed++;
            } else {
                speed = maxSpeed;
            }
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public TransportModels getModel() {
        return model;
    }

    public void setModel(TransportModels model) {
        this.model = model;
    }

    @Override
    public void print() {
        System.out.println("Model: " + model);
        System.out.println("Registration number: " + registrationNumber);
        System.out.println("Type: " + type);
        System.out.println("Capacity: " + capacity);
        System.out.println("Price: " + price);
        System.out.println("Fuel consumption: " + fuelConsumption);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

