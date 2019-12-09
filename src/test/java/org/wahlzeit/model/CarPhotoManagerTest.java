package org.wahlzeit.model;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import java.io.IOException;
import static org.junit.Assert.assertEquals;


public class CarPhotoManagerTest {


   @ClassRule
  public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());

    @Test
    public void testInitialize() {
        CarPhotoManager.getInstance().init();
    }

    @Test
    public void testCarPhotoManager() throws IOException {
        CarPhotoManager carPhotoManager = new CarPhotoManager();
        CarPhoto carPhoto = new CarPhoto(new PhotoId(123456), "Ferrari", "Enzo", "red");
        carPhotoManager.addPhoto(carPhoto);
        CarPhoto testPhoto = carPhotoManager.getPhotoFromId(carPhoto.getId());
        assertEquals(carPhoto, testPhoto);
        assertEquals(carPhoto.getBrand(), testPhoto.getBrand());
        assertEquals(carPhoto.getModel(), testPhoto.getModel());
        assertEquals(carPhoto.getColor(), testPhoto.getColor());
        CarPhotoManager carPhotoManager1 = new CarPhotoManager();
        CarPhoto testPhoto1 = carPhotoManager1.getPhotoFromId(null);
        assertEquals(null, testPhoto1);
    }
    @Test
    public void testLoadPhoto() throws IOException {
    CarPhotoManager carPhotoManager = new CarPhotoManager();
    CarPhoto carPhoto = new CarPhoto(new PhotoId(123456), "Ferrari", "Enzo", "red");
    carPhotoManager.addPhoto(carPhoto);
    carPhotoManager.loadPhotos();
    }
}




