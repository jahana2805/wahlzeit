package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CarPhoto extends Photo {

private String brand;
private String model;
private String color;


    public CarPhoto(String brand, String model, String color){
        super();
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Car should be provided with a brand name!");}
        else if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Car should be provided with a model name!");}
        else if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Car should be provided with a color!");}
    this.brand = brand;
    this.model = model;
    this.color = color;
}
    public CarPhoto(PhotoId id, String brand, String model, String color){
        super(id);
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Car should be provided with a brand name!");}
        else if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Car should be provided with a model name!");}
        else if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Car should be provided with a color!");}
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public String getBrand(){
       return brand;
    }
    public String getModel(){
        return model;
    }
    public String getColor(){
        return color;
    }


}
