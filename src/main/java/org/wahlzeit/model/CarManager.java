package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;
/** Manager collaboration
 * Centralize object management in one place
 */


public class CarManager extends ObjectManager {
    private HashMap<String, CarType> carTypeHashMap = new HashMap<>();
    private HashMap<Long, Car> cars = new HashMap<>();
    private static CarManager instance = new CarManager();
    private String color;

    private CarManager() {
        super();
    }

    public static CarManager getInstance() {

        return instance;
    }
    public Car createCar(CarType carType) {
        return doCreateCar(carType.getTypeName());
    }
    public Car createCar(String carTypeName, String color) {

            Car newCar = doCreateCar(carTypeName);
            newCar.setColor(color);
            return newCar;
        }
    private Car doCreateCar(String carTypeName) {
        assertValueIsNotNullEmpty(carTypeName);
        CarType ct = this.getCarType(carTypeName);
        Car result = ct.createInstance(color);
        cars.put(result.getId(), result);
        return result;
    }
    public CarType getCarType(String carTypeName){
        if (carTypeHashMap.containsKey(carTypeName)){
        return carTypeHashMap.get(carTypeName);
        }else{
            CarType newCarType = new CarType(carTypeName);
            this.carTypeHashMap.put(carTypeName, newCarType);
            return newCarType;
        }
    }
    private void assertValueIsNotNullEmpty(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Shouldn't be null or empty!");
        }
    }

    private void assertValueIsNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Shouldn't be null!");
        }
    }


    public synchronized CarType createType(String typeName, String brand, String model) {
        assertValueIsNotNullEmpty(typeName);
        assertValueIsNotNullEmpty(brand);
        CarType ct = new CarType(typeName, this, brand, model, null);
        return doCreateCarType(ct);
    }

    public synchronized CarType createSubType(String typeName, String brand, String model, CarType subType) {
        assertValueIsNotNullEmpty(typeName);
        assertValueIsNotNullEmpty(brand);
        assertValueIsNotNull(subType);
        CarType ct = new CarType(typeName, this, brand, model, subType);
        return doCreateCarType(ct);
    }

    private CarType doCreateCarType(CarType carType) {
            carTypeHashMap.put(carType.getTypeName(), carType);
        assert(this.carTypeHashMap.containsKey(carType.getTypeName()));
            return carType;
        }


    }
