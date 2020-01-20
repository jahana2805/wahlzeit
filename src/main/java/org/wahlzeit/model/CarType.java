package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class CarType extends DataObject {

    private String typeName = null;
    private CarManager carManager = null;
    private CarType subType = null;
    private String brand = null;
    private String model = null;

    protected CarType(String typeName, CarManager carManager, String brand, String model, CarType subType) {
this.typeName = typeName;
this.carManager = carManager;
this.brand = brand;
this.model = model;
this.subType = subType;
    }
    public Car createInstance(String color, CarManager carManager) {
        return new Car(this, color, carManager);
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
}
