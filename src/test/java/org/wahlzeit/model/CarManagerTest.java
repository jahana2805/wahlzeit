package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CarManagerTest {
    private CarManager carManager = null;

    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        assertNotNull(carManager);
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
