package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CarPhotoFactoryTest {
    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());

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
        CarPhotoFactory carPhotoFactory = new CarPhotoFactory();
        CarPhoto carPhoto = carPhotoFactory.createPhoto(new PhotoId(123456), "Ferrari", "Enzo", "red");
        Assert.assertEquals(carPhoto.getId().asInt(),123456);
        Assert.assertEquals(carPhoto.getBrand(),"Ferrari");
        Assert.assertEquals(carPhoto.getModel(),"Enzo");
        Assert.assertEquals(carPhoto.getColor(),"red");
    }
    @Test
    public void testCreatePhotoWithoutId() throws CreateCarPhotoException {
        CarPhotoFactory carPhotoFactory = new CarPhotoFactory();
        CarPhoto carPhoto = carPhotoFactory.createPhoto("Ferrari", "Enzo", "red");
        Assert.assertEquals(carPhoto.getBrand(),"Ferrari");
        Assert.assertEquals(carPhoto.getModel(),"Enzo");
        Assert.assertEquals(carPhoto.getColor(),"red");
    }
    @Test(expected = CreateCarPhotoException.class)
    public void testNullBrandThrowsException() throws CreateCarPhotoException {
        CarPhotoFactory.getInstance().createPhoto(null, "Enzo", "red");
    }

    @Test(expected = CreateCarPhotoException.class)
    public void testNullModelThrowsException() throws CreateCarPhotoException {
        CarPhotoFactory.getInstance().createPhoto(new PhotoId(123456),"Ferrari", null, "red");
    }
    @Test(expected = CreateCarPhotoException.class)
    public void testNullColorThrowsException() throws CreateCarPhotoException {
        CarPhotoFactory.getInstance().createPhoto("Ferrari", "Enzo", null);
    }

    }

