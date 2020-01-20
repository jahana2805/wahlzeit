package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;
import java.util.Map;

public class CarManager extends ObjectManager {
    private HashMap<String, CarType> carTypeHashMap = new HashMap<>();
    private Map<Long, Car> cars = new HashMap<>();
    private static CarManager instance = null;

    public static CarManager getInstance() {
        if (instance == null) {
            instance = new CarManager();
        }
        return instance;
    }
    public Car createCar(String carTypeName, String color, CarManager carManager) {
        assertValueIsNotNullEmpty(carTypeName);
        CarType ct = carTypeHashMap.get(carTypeName);
        Car result = ct.createInstance(color, carManager);
        cars.put(result.getId(), result);
        return result;
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
        carTypeHashMap.put(ct.getTypeName(), ct);
        if (!this.carTypeHashMap.containsKey(ct.getTypeName())){
            throw new IllegalArgumentException("Should contain typename in a hashmap!");
        }
        return ct;
    }
    public synchronized CarType createSubType(String typeName, String brand, String model, CarType subType) {
        assertValueIsNotNullEmpty(typeName);
        assertValueIsNotNullEmpty(brand);
        assertValueIsNotNull(subType);
        CarType ct = new CarType(typeName, this, brand, model, subType);
        carTypeHashMap.put(ct.getTypeName(), ct);
        if (!this.carTypeHashMap.containsKey(ct.getTypeName())){
            throw new IllegalArgumentException("Should contain typename in hashmap!");
        }
        return ct;
    }
    }
