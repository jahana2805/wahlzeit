package org.wahlzeit.model;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.OfyService;

import java.util.logging.Logger;

public class CarPhotoFactory extends PhotoFactory{

    private static final Logger log = Logger.getLogger(CarPhotoFactory.class.getName());
    private static CarPhotoFactory instance = null;
    private String filename;

    protected CarPhotoFactory() {
        super();
    }


    public static void initialize() {
        getInstance();
    }

    public static synchronized CarPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic CarPhotoFactory").toString());
            setInstance(new CarPhotoFactory());
        }

        return instance;
    }


    protected static synchronized void setInstance(CarPhotoFactory carphotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize CarPhotoFactory twice");
        }

        instance = carphotoFactory;
    }


    public CarPhoto createPhoto(String brand, String model, String color) {
        return new CarPhoto(brand, model, color);
    }

    public CarPhoto createPhoto(PhotoId id, String brand, String model, String color) {
        return new CarPhoto(id, brand, model, color);
    }


    @Override
    public CarPhoto loadPhoto(PhotoId id) {
        return (CarPhoto)super.loadPhoto(id);
    }
}

