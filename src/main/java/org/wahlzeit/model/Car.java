package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Id;

import java.util.concurrent.atomic.AtomicLong;

public class Car {
    @Id
    private final long id;
    private static AtomicLong nextId = new AtomicLong();

    private CarType carType;
    private CarManager carManager;
    private String color;

    public Car(CarType carType, String color, CarManager carManager) {
        assertValueIsNotNull(carManager);
        assertValueIsNotNull(carType);
        this.carType = carType;
        this.color = color;
        this.carManager = carManager;
        this.id = Car.getId();
    }

    public static Long getId() {
        return nextId.getAndIncrement();
    }
    public CarType getCarType(){
        return this.carType;
    }
    public CarManager getCarManager(){
        return this.carManager;
    }
    public String getColor(){
        return color;
    }
    public String getModel(){
        return this.getCarType().getModel();
    }
    public String getBrand(){
        return this.getCarType().getBrand();
    }
    private void assertValueIsNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Shouldn't be null!");
        }
    }
}
