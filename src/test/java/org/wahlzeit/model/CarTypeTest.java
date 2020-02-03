package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarTypeTest {
    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());

    private CarManager carManager = null;
    private CarType carType1 = null;
    private CarType carType2 = null;
    private CarType carTypeWithSubtype = null;
    @Before
    public void setup() {
        carManager = CarManager.getInstance();
        this.carType1 = carManager.createType("carType1", "Ferrari", "Enzo");
        this.carType2 = carManager.createType( "carType2", "Mercedes-Benz", "GLA");
        this.carTypeWithSubtype = carManager.createSubType("carType1",  "Ferrari", "Enzo", carType1);
    }

    @Test
    public void testIsSubtypeWorksForNonSubtype() {
        assertFalse(carType1.isSubtype());
    }

    @Test
    public void testIsSubtypeWorksForSubtype() {
        assertTrue(carTypeWithSubtype.isSubtype());
    }
    @Test


    public void testConstructorSetsCorrectValues() {
        Assert.assertEquals(this.carType1.getBrand(), "Ferrari");
        Assert.assertEquals(this.carType1.getModel(), "Enzo");
    }


}

