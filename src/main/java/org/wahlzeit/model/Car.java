package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Id;
import org.wahlzeit.services.DataObject;

import java.util.concurrent.atomic.AtomicLong;
/**
 *
 * Instantiation process of a Car:
 * The process starts in CarManager, by calling getInstance() singleton method;
 * for creating CarType instance is used method createInstance(),
 * method doCreateCarType() serves for product trader pattern by adding new types(subtype, supertype, etc.)
 * in the same CarManager class, by using hashmap, for thread-safe implementation.
 * method doCreateCar() also serves as a product trader pattern for methods: createCar(String carTypeName) and
 * createCar(CarType carType) with different properties.
 */
public class Car extends DataObject {
    @Id
    private long id;
    private static AtomicLong nextId = new AtomicLong(0);

    private CarType carType;
    private CarManager carManager;
    private String color;

    public Car(CarType carType, CarManager carManager, String color) {

        this.carType = carType;
        this.carManager = carManager;
        this.color = color;
        this.id = Car.getId();
    }
    /**
     * Set manufacturing color.
     *
     * @param color
     */

    public void setColor(String color) {
        assertValueIsNotNullEmpty(color);
        this.color = color;
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

    private void assertValueIsNotNullEmpty(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Shouldn't be null or empty!");
        }
    }
}
