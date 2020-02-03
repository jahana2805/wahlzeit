package org.wahlzeit.model;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CarTest {
    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());

    private CarManager carManager;
    private CarType carType;
    private String typeName;
    private CarType typeNameNull = null;


    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        typeName = "Typename";
        carType = carManager.createType(typeName,"Ferrari", "Enzo");

    }


    @Test
    public void testConstructorThrowsExceptionOnNullCarType() throws CreateCarPhotoException {
        Car car = new Car(typeNameNull, carManager, "red");

    }

    @Test
    public void testConstructorThrowsAssertionErrorOnNullCarManager() throws CreateCarPhotoException {

        Car car = new Car(carType, null, "red");
    }
}
