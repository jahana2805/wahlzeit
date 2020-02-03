package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class CarType extends DataObject {

    private String typeName = null;
    private CarManager carManager = null;
    private CarType subType = null;
    private String brand = null;
    private String model = null;

    protected CarType(String typeName){
        this.typeName = typeName;
    }
    protected CarType(String typeName, CarManager carManager, String brand, String model, CarType subType) {
this.typeName = typeName;
this.carManager = carManager;
this.brand = brand;
this.model = model;
this.subType = subType;
    }
    public Car createInstance(String color) {
        return new Car(this, carManager, color);
    }

    public String getModel(){
        return model;
}
    public String getBrand(){
        return brand;
    }
    public String getTypeName(){
        return this.typeName;
    }
    public CarManager getCarManager() {
        return carManager;
    }
    public boolean isSubtype() {
        return subType != null;
    }
    /**
     * Set the displayed car's model.
     *
     * @param model
     */

    public void setModel(String model) {
        assertValueIsNotNullEmpty(model);
        this.model = model;
    }
    /**
     * Set the displayed car's brand.
     *
     * @param brand
     */

    public void setBrand(String brand) {
        assertValueIsNotNullEmpty(brand);
        this.brand = brand;
    }
    private void assertValueIsNotNullEmpty(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Shouldn't be null or empty!");
        }
    }

}
