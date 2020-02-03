package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;

public class CarPhotoTest {
    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());

    private CarManager carManager = null;
    private String typeName;
    private CarType carType = null;

    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        typeName = "Typename";
        carType = carManager.createType(typeName, "Ferrari", "Enzo");
    }

    @Test
    public void testCarPhoto() throws CreateCarPhotoException {
        Car car = new Car(carType, carManager, "red");
        CarPhoto photo = new CarPhoto(new PhotoId(123456), car);
        Assert.assertNotNull(photo.getCar());
        assertEquals(photo.getCar(), car);


    }
   }