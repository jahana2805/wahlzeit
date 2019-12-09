package org.wahlzeit.model;

import java.util.logging.Logger;

public class CarPhotoManager extends PhotoManager {
    protected static CarPhotoManager instance = new CarPhotoManager();
    private static final Logger log = Logger.getLogger(CarPhotoManager.class.getName());

    public static CarPhotoManager getInstance() {
        return instance;
    }
    /**
     * @methodtype get
     * @methodproperties primitive
     */
    @Override
    public CarPhoto getPhoto(PhotoId id) {
        return (CarPhoto)super.getPhoto(id);
    }
    /**
     * @methodtype get by id
     * @methodproperties primitive
     */
    @Override
    public CarPhoto getPhotoFromId(PhotoId id) {
        return (CarPhoto)super.getPhotoFromId(id);
    }
    /**
     * @methodtype command
     *
     * Load all persisted photos by calling its superclass
     */
    @Override
    public void loadPhotos() {
        super.loadPhotos();
    }

}
