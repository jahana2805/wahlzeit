package org.wahlzeit.model;

import com.google.appengine.api.images.Image;

import java.io.IOException;
import java.util.logging.Logger;

public class CarPhotoManager extends PhotoManager {
    protected static CarPhotoManager instance = new CarPhotoManager();
    private static final Logger log = Logger.getLogger(CarPhotoManager.class.getName());

    public static CarPhotoManager getInstance() {
        return instance;
    }

    @Override
    protected boolean doHasPhoto(PhotoId id) {
        return super.doHasPhoto(id);
    }

    @Override
    public CarPhoto getPhoto(PhotoId id) {
        return (CarPhoto)super.getPhoto(id);
    }

    @Override
    public CarPhoto getPhotoFromId(PhotoId id) {
        return (CarPhoto)super.getPhotoFromId(id);
    }

    @Override
    public void loadPhotos() {
        super.loadPhotos();
    }
    public void savePhoto(CarPhoto carPhoto) {
        super.savePhoto(carPhoto);
    }
}
