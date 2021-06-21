package classmodel;

import static classmodel.enums.TransportModels.Lada;
import static classmodel.enums.TransportModels.Nissan;
import static classmodel.enums.TransportModels.Renault;
import static classmodel.enums.TransportModels.Scania;
import static classmodel.enums.TransportModels.Toyota;
import static classmodel.enums.TransportModels.Volga;
import static classmodel.enums.TransportModels.Yamaha;
import static classmodel.enums.TransportTypes.bus;
import static classmodel.enums.TransportTypes.taxi;
import static classmodel.enums.TransportTypes.water_taxi;

import classmodel.base.Comparator;
import classmodel.base.PublicTransport;
import classmodel.enums.TransportModels;
import classmodel.enums.TransportTypes;
import classmodel.exceptions.CapacityException;
import classmodel.exceptions.ConsumptionException;
import classmodel.exceptions.PriceException;
import classmodel.land.FuelLandTransport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Program  {
    public static void main(String[] args) {
        List<PublicTransport> transportPark = new ArrayList<PublicTransport>();

        PublicTransport bus1 = new FuelLandTransport(Nissan, "dfg123gh", 250, 100000, bus, 20, 850000);
        transportPark.add(bus1);

        PublicTransport bus2 = new FuelLandTransport(Scania, "kjy173rt", 450, 150000, bus, 20, 990000);
        transportPark.add(bus2);

        PublicTransport bus3 = new FuelLandTransport(Nissan, "thi657hn", 250, 100000, bus, 18, 1350000);
        transportPark.add(bus3);

        PublicTransport bus4 = new FuelLandTransport(Nissan, "hty983gb", 250, 100000, bus, 18, 1250000);
        transportPark.add(bus4);

        PublicTransport car1 = new FuelLandTransport(Lada, "935ghc67", 150, 50000, bus, 5, 50500);
        transportPark.add(car1);

        PublicTransport car2 = new FuelLandTransport(Renault, "asc657jm", 180, 60000, taxi, 5, 350000);
        transportPark.add(car2);

        PublicTransport car3 = new FuelLandTransport(Toyota, "rfb547hn", 350, 65000, taxi, 8, 1540500);
        transportPark.add(car3);

        PublicTransport car4 = new FuelLandTransport(Nissan, "546hng45", 150, 30000, taxi, 5, 760500);
        transportPark.add(car4);

        PublicTransport car5 = new FuelLandTransport(Volga, "148hnbf56", 250, 15000, taxi, 5, 100000);
        transportPark.add(car5);

        PublicTransport motorBoat1 = new FuelLandTransport(Yamaha, "df5698gh", 150, 9000, water_taxi, 10, 550000);
        transportPark.add(motorBoat1);

        System.out.println("В транспортном парке имеются в наличии следующие средства передвижения: ");
        for (PublicTransport transport : transportPark) {
            transport.print();
            System.out.println(" ");
        }

        System.out.println("Суммарная стоимость транспорта в парке равна: " + summPrice(transportPark));

        Collections.sort(transportPark, new Comparator());
        System.out.println("Отсортированный список транспорта в парке равна (по расходу топлива): ");
        for (PublicTransport transport : transportPark) {
            transport.print();
            System.out.println(" ");
        }

        System.out.println("Введите параметры для поиска: ");

        Scanner scan = new Scanner(System.in);

        System.out.println("Тип: ");
        TransportTypes type = TransportTypes.valueOf(scan.nextLine());
        searchByType(type, transportPark).print();

        System.out.println("Модель: ");
        TransportModels model = TransportModels.valueOf(scan.nextLine());
        searchByModel(model, transportPark).print();

        System.out.println("Вместимость: ");
        System.out.println("Минимальное значение: ");
        int capacityMin = scan.nextInt();
        System.out.println("Максимальное значение: ");
        int capacityMax = scan.nextInt();
        try {
            searchByCapacity(capacityMin, capacityMax, transportPark).print();
        } catch (CapacityException e) {
            e.printStackTrace();
        }

        System.out.println("Цена: ");
        System.out.println("Минимальное значение: ");
        int priceMin = scan.nextInt();
        System.out.println("Максимальное значение: ");
        int priceMax = scan.nextInt();
        try {
            searchByPrice(priceMin, priceMax, transportPark).print();
        } catch (PriceException e) {
            e.printStackTrace();
        }

        System.out.println("Расход топлива: ");
        System.out.println("Минимальное значение: ");
        int consumptionMin = scan.nextInt();
        System.out.println("Максимальное значение: ");
        int consumptionMax = scan.nextInt();
        try {
            searchByConsumption(consumptionMin, consumptionMax, transportPark).print();
        } catch (ConsumptionException e) {
            e.printStackTrace();
        }
    }

    public static int summPrice(List<PublicTransport> list) {
        int summ = 0;
        for (PublicTransport transport : list) {
            summ += transport.getPrice();
        }
        return summ;
    }

    public static PublicTransport searchByType(TransportTypes type, List<PublicTransport> list) {
        PublicTransport result = new PublicTransport();
        boolean exists = true;

        try {
            TransportTypes.valueOf(String.valueOf(type));
        } catch (IllegalArgumentException e) {
            exists = false;
        }

        for (PublicTransport transport : list) {
            if (transport.getType() == type) {
                result = transport;
                return result;
            }
        }
        return result;
    }

    public static PublicTransport searchByModel(TransportModels model, List<PublicTransport> list) {
        PublicTransport result = new PublicTransport();
        boolean exists = true;

        try {
            TransportModels.valueOf(String.valueOf(model));
        } catch (IllegalArgumentException e) {
            exists = false;
        }

        for (PublicTransport transport : list) {
            if (transport.getModel() == model) {
                result = transport;
            }
        }
        return result;
    }

    public static PublicTransport searchByCapacity(int capacityMin, int capacityMax, List<PublicTransport> list)
                                                   throws CapacityException {
        PublicTransport result = new PublicTransport();

        if (capacityMax < 0 || capacityMin < 0) {
            throw new CapacityException("Capacity can't be less than zero!", capacityMax, capacityMin);
        }

        for (PublicTransport transport : list) {
            if (capacityMin < transport.getCapacity() && transport.getCapacity() < capacityMax) {
                result = transport;
            }
        }
        return result;
    }

    public static PublicTransport searchByPrice(int priceMin, int priceMax, List<PublicTransport> list)
                                               throws PriceException {
        PublicTransport result = new PublicTransport();

        if (priceMin < 0 || priceMax < 0) {
            throw new PriceException("Price can't be less than zero!", priceMax, priceMin);
        }

        for (PublicTransport transport : list) {
            if (priceMin < transport.getPrice() && transport.getPrice() < priceMax) {
                result = transport;
            }
        }
        return result;
    }

    public static PublicTransport searchByConsumption(int consumptionMin, int consumptionMax,
                                                      List<PublicTransport> list)
                                                      throws ConsumptionException {
        PublicTransport result = new PublicTransport();

        if (consumptionMin < 0 || consumptionMax < 0) {
            throw new ConsumptionException("Fuel consumption can't be less than zero!", consumptionMax, consumptionMin);
        }

        for (PublicTransport transport : list) {
            if (consumptionMin < transport.getFuelConsumption() && transport.getFuelConsumption() < consumptionMax) {
                result = transport;
            }
        }
        return result;
    }
}
