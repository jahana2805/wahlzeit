package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
/**
 *
 * Instantiation process of a CarPhoto:
 * In CarPhoto, CarPhotoFactory, CarPhotoManager object was created by the use of Factory Method.
 * It starts with initialize() method(initialization) in CarPhotoFactory,
 * then calls singleton getInstance()(instantiation), which is calling setInstance();
 * then methods createPhoto(), which are Factory methods, are implemented on subclass CarPhotoFactory.
 */

/** Client-Node-Collaboration
 * provide main domain functionality
 */

@Subclass
public class CarPhoto extends Photo {
private Car car = null;
    public CarPhoto() {
        super();
    }
    /**
     * constructor
     *
     * @param car
     */
    public CarPhoto(Car car){
        super();
        assertValueIsNotNull(car);

    this.car = car;

}
    private void assertValueIsNotNull(Object o){
        if(o == null) {
            throw new IllegalArgumentException("Shouldn't be null!");
        }
    }


    /**
     * constructor with ID
     *
     * @param id
     * @param car
     */
    public CarPhoto(PhotoId id, Car car){
        super(id);
assertValueIsNotNull(id);
assertValueIsNotNull(car);
        this.id = id;
        this.car = car;
    }
    /**
     * Displays the car.
     *
     * @return car object.
     */
    public Car getCar(){
       return this.car;
    }

}
