package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static junit.framework.TestCase.assertTrue;

public class CarPhotoFactoryTest {
    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());
    private CarManager carManager = null;
    private Car car = null;


    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        car = carManager.createCar("sportscar", "red", carManager);
    }
    @Test
    public void testInitialization(){
        CarPhotoFactory.initialize();
    }

    @Test(expected = IllegalStateException.class)
    public void testSetInstanceTwiceInitException() {
        CarPhotoFactory.initialize();
        CarPhotoFactory.setInstance(new CarPhotoFactory());
    }
    @Test
    public void testCreatePhotoWithId() throws CreateCarPhotoException {
        CarPhotoFactory carPhotoFactory = CarPhotoFactory.getInstance();
        CarPhoto carPhoto = carPhotoFactory.createPhoto(new PhotoId(123456),car);
        Assert.assertEquals(carPhoto.getId().asInt(),123456);
        Assert.assertEquals(carPhoto.getCar().getBrand(), car.getBrand());
        Assert.assertEquals(carPhoto.getCar().getModel(),car.getModel());
        Assert.assertEquals(carPhoto.getCar().getColor(), car.getColor());
    }
    @Test
    public void testCreatePhotoWithoutId() throws CreateCarPhotoException {
        CarPhotoFactory carPhotoFactory = CarPhotoFactory.getInstance();
        CarPhoto carPhoto = carPhotoFactory.createPhoto(car);
        assertTrue(carPhoto instanceof CarPhoto);
        Assert.assertEquals(carPhoto.getCar().getBrand(),car.getBrand());
        Assert.assertEquals(carPhoto.getCar().getModel(),car.getModel());
        Assert.assertEquals(carPhoto.getCar().getColor(), car.getColor());
    }



    }

