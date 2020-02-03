package org.wahlzeit.model;

import org.junit.Before;
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
    private CarManager carManager = null;
    private CarType carType = null;
    private CarType carType2 = null;
    private String typeName;
    private Car car;

    @Before
    public void setup() throws CreateCarPhotoException {
        carManager = CarManager.getInstance();
        carType = carManager.createType("sportscar","Ferrari", "Enzo");
        car = carManager.createCar("sportscar", "red", carManager);

         }

    @Test
    public void testCarPhotoManager() throws IOException {
        CarPhotoManager carPhotoManager = new CarPhotoManager();
        CarPhoto carPhoto = new CarPhoto(new PhotoId(123456), car);
        carPhotoManager.addPhoto(carPhoto);
        CarPhoto testPhoto = carPhotoManager.getPhotoFromId(carPhoto.getId());
        assertEquals(carPhoto, testPhoto);
        assertEquals(carPhoto.getCar().getBrand(), testPhoto.getCar().getBrand());
        assertEquals(carPhoto.getCar().getModel(), testPhoto.getCar().getModel());
        assertEquals(carPhoto.getCar().getColor(), testPhoto.getCar().getColor());
        CarPhotoManager carPhotoManager1 = new CarPhotoManager();
        CarPhoto testPhoto1 = carPhotoManager1.getPhotoFromId(null);
        assertEquals(null, testPhoto1);
    }
    @Test
    public void testLoadPhoto() throws IOException {
    CarPhotoManager carPhotoManager = new CarPhotoManager();
    CarPhoto carPhoto = new CarPhoto(new PhotoId(123456), car);
    carPhotoManager.addPhoto(carPhoto);
    carPhotoManager.loadPhotos();
    }
}




