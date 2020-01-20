package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

public class CarTest {
    private CarManager carManager;
    private CarType carType;
    private String typeName;
    private String color;


    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        typeName = "Typename";
        carType = carManager.createType(typeName,"Ferrari", "Enzo");
        color = "red";
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionOnNullCarType() {
        Car car = new Car(null, color, carManager);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsAssertionErrorOnNullCarManager() {

        Car car = new Car(carType, color,null);
    }
}
