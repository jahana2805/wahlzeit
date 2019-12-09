package org.wahlzeit.model;

public class CreateCarPhotoException extends Exception {
    public CreateCarPhotoException(String message) {
        super(message);
    }
    public CreateCarPhotoException(IllegalArgumentException e) {
        super(e);
    }
}
