package classmodel.land;

import classmodel.base.Movable;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;

public class AnimalLandTransport extends LandTransport implements Movable {
    private boolean hasTrailer;
    private String kindOfAnimal;

    public boolean isHasTrailer() {
        return hasTrailer;
    }

    public void setHasTrailer(boolean hasTrailer) {
        this.hasTrailer = hasTrailer;
    }

    public String getKindOfAnimal() {
        return kindOfAnimal;
    }

    public void setKindOfAnimal(String kindOfAnimal) {
        this.kindOfAnimal = kindOfAnimal;
    }

    AnimalLandTransport(TransportModels model, String registrationNumber, String kindOfAnimal,
                        boolean hasTrailer, TransportTypes type, int capacity, int price) {
        this.setModel(model);
        this.setRegistrationNumber(registrationNumber);
        this.kindOfAnimal = kindOfAnimal;
        this.hasTrailer = hasTrailer;
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
    }
}
