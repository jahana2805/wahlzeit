package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarTypeTest {
    private CarManager carManager = null;
    private CarType carType1 = null;
    private CarType carType2 = null;
    private CarType carTypeWithSubtype = null;
    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        this.carType1 = new CarType("carType1", carManager, "Ford", null, null);
        this.carType2 = new CarType( "carType2", carManager, "Mercedes-Benz", null, null);
        this.carTypeWithSubtype = new CarType("carType3", carManager, "Fiat", null, carType2);
    }

    @Test
    public void testIsSubtypeWorksForNonSubtype() {
        assertFalse(carType1.isSubtype());
    }

    @Test
    public void testIsSubtypeWorksForSubtype() {
        assertTrue(carTypeWithSubtype.isSubtype());
    }


}

