package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CarManagerTest {
    private CarManager carManager = null;
    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());



    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        Assert.assertNotNull(carManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCarTypeWithNullBrand() {
        CarType shouldNotBeCreated = carManager.createType(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCarTypeWithEmptyBrand() {
        CarType shouldNotBeCreated = carManager.createType("", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCarTypeWithNullTypeName() {
        CarType shouldNotBeCreated = carManager.createType(null, "Ford", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCarTypeWithEmptyTypeName() {
        CarType shouldNotBeCreated = carManager.createType("", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCarSubTypeWithNullBaseClass() {
        CarType shouldNotBeCreated = carManager.createSubType(null, "Ford", null, null);
    }

}
