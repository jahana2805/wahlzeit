package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CarPhoto extends Photo {

private String brand;
private String model;
private String color;

    /**
     * constructor
     *
     * @param brand
     * @param model
     * @param color
     */
    public CarPhoto(String brand, String model, String color){
        super();
        assertValueIsNotNull(brand);
        assertValueIsNotNull(model);
        assertValueIsNotNull(color);
    this.brand = brand;
    this.model = model;
    this.color = color;
       assertClassInvariants();
}
    private void assertValueIsNotNull(String str){
        if(str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Shouldn't be null!");
        }
    }
    private void assertClassInvariants() {
        assertValueIsNotNull(brand);
        assertValueIsNotNull(model);
        assertValueIsNotNull(color);
    }

    /**
     * constructor with ID
     *
     * @param id
     * @param brand
     * @param model
     * @param color
     */
    public CarPhoto(PhotoId id, String brand, String model, String color){
        super(id);

        assertValueIsNotNull(brand);
        assertValueIsNotNull(model);
        assertValueIsNotNull(color);

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        assertClassInvariants();
    }
    /**
     * Get brand value.
     *
     * @return brand value of CarPhoto.
     */
    public String getBrand(){
       return brand;
    }
    /**
     * Get model value.
     *
     * @return model value of CarPhoto.
     */
    public String getModel(){
        return model;
    }
    /**
     * Get color value.
     *
     * @return color value of CarPhoto.
     */
    public String getColor(){
        return color;
    }


}
