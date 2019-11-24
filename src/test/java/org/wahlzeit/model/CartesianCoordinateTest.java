package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartesianCoordinateTest {
    double epsilon = 1e-4;
    CartesianCoordinate isNull;
    CartesianCoordinate c1;
    CartesianCoordinate c2;
    CartesianCoordinate c3;

    @Before
    public void setup() {
        c1 = new CartesianCoordinate(1.1, 2.5, 5.5);
        c2 = new CartesianCoordinate(1.1, 2.5, 5.5);
        c3 = new CartesianCoordinate(2.9, 3.9, 9.9);
        isNull = null;
    }

    @Test
    public void testGetCartesianDistance__equalC() {
        double actual = c1.getCartesianDistance(c2);
        Assert.assertEquals("Wrong", 0, actual, epsilon);
    }

    @Test
    public void testGetCartesianDistance__not_equalC() {
        double actual = c1.getCartesianDistance(c3);
        Assert.assertEquals("Wrong", 4.9558, actual, epsilon);
    }

    @Test
    public void testIsEqual() {
        CartesianCoordinate c4 = new CartesianCoordinate(5.5, Double.NaN, 2);
        CartesianCoordinate c5 = new CartesianCoordinate(Double.NaN, 1, 2);
        CartesianCoordinate c6 = new CartesianCoordinate(4.2, 5.5, Double.NaN);
        assertTrue(c1.isEqual(c2));
        assertFalse(c1.isEqual(c3));
        assertFalse(c4.isEqual(c1));
        assertFalse(c5.isEqual(c2));
        assertFalse(c6.isEqual(c3));
        assertFalse(c1.isEqual(isNull));
    }

    @Test
    public void testEquals__diffObj() {
        Object o = "abc";
        assertFalse(c1.equals(o));
    }

    @Test
    public void testEquals__Obj() {
        Object o1 = new Object();
        assertFalse(c1.equals(o1));
        assertFalse(c1.hashCode() == o1.hashCode());
    }

    @Test
    public void testEquals__Same() {
        assertTrue(c1.equals(c2));
        assertTrue(c1.hashCode() == c2.hashCode());
    }

    @Test
    public void testEquals__Diff() {
        assertFalse(c1.equals(c3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowException() {
        c1.getCartesianDistance(isNull);
    }

    @Test
    public void testAsCartesianCoordinate() {
        Assert.assertSame(c1, c1.asCartesianCoordinate());
    }

    @Test
    public void testAsSphericCoordinate() {
        SphericCoordinate sphericCoordinate = c1.asSphericCoordinate();
        assertTrue(sphericCoordinate instanceof SphericCoordinate);
        Assert.assertEquals(6.1408468, sphericCoordinate.getRadius(), epsilon);
        Assert.assertEquals(0.4609239, sphericCoordinate.getTheta(), epsilon);
        Assert.assertEquals(1.1562894, sphericCoordinate.getPhi(), epsilon);
    }

   @Test
    public void testGetCentralAngle() {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(180.0, 45.0, 900);
        Assert.assertEquals(2.787399, c1.getCentralAngle(sphericCoordinate), epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistance_Exception() {
            c1.getCentralAngle(isNull);
        }
    }


