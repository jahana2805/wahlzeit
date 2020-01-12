package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

/**
 * Factory that creates car photos
 */
public class CarPhotoFactory extends PhotoFactory{

    private static final Logger log = Logger.getLogger(CarPhotoFactory.class.getName());
    private static CarPhotoFactory instance = null;

    /**
     * constructor
     */
    protected CarPhotoFactory() {
        super();
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance();
    }
    /**
     * Public singleton access method.
     */
    public static synchronized CarPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic CarPhotoFactory").toString());
            setInstance(new CarPhotoFactory());
        }
        return instance;
    }
    /**
     * Method to set the singleton instance of CarPhotoFactory.
     */
    protected static synchronized void setInstance(CarPhotoFactory carphotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize CarPhotoFactory twice");
        }

        instance = carphotoFactory;
    }

    /**
     * factory
     * @throws CreateCarPhotoException
     */
    public CarPhoto createPhoto(String brand, String model, String color) throws CreateCarPhotoException {
        CarPhoto carPhoto = null;
        try {
            carPhoto = new CarPhoto(brand, model, color);
        } catch(IllegalArgumentException e) {
            throw new CreateCarPhotoException("Couldn't create a CarPhoto in CarPhotoFactory(" + e.getMessage() + ")");
        }
        return carPhoto;
    }
    /**
     * Creates a new photo with the id
     * @throws CreateCarPhotoException
     */
    public CarPhoto createPhoto(PhotoId id, String brand, String model, String color) throws CreateCarPhotoException {
        CarPhoto carPhoto = null;
        try {
            carPhoto = new CarPhoto(id, brand, model, color);
        } catch(IllegalArgumentException e) {
            throw new CreateCarPhotoException("Couldn't create a CarPhoto by id in CarPhotoFactory(" + e.getMessage() + ")");
        }
        return carPhoto;
    }

}

