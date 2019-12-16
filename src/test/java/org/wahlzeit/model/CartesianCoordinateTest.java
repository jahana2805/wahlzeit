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
        c1 = CartesianCoordinate.getInstance(1.1,2.5,5.5);
        c2 = CartesianCoordinate.getInstance(1.1, 2.5, 5.5);
        c3 = CartesianCoordinate.getInstance(2.9, 3.9, 9.9);
        isNull = null;
    }
    @Test(expected = AssertionError.class)
    public void testXIsInfinity() {
        new CartesianCoordinate(Double.POSITIVE_INFINITY, 2.5, 5.5);
    }

    @Test(expected = AssertionError.class)
    public void testXIsDoubleNaN() {
        new CartesianCoordinate(Double.NaN, 2.5 , 5.5);
    }
    @Test(expected = AssertionError.class)
    public void testYIsInfinity() {
        new CartesianCoordinate(2.5, Double.POSITIVE_INFINITY, 5.5);
    }

    @Test(expected = AssertionError.class)
    public void testYIsDoubleNaN() {
        new CartesianCoordinate(2.5 , Double.NaN,5.5);
    }
    @Test(expected = AssertionError.class)
    public void testZIsInfinity() {
        new CartesianCoordinate(2.5, 5.5, Double.POSITIVE_INFINITY);
    }

    @Test(expected = AssertionError.class)
    public void testZIsDoubleNaN() {
        new CartesianCoordinate(2.5 , 5.5, Double.NaN);
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
        assertTrue(c1.isEqual(c2));
        assertFalse(c1.isEqual(c3));
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
        Assert.assertEquals(c2, c1);
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
        SphericCoordinate sphericCoordinate = new SphericCoordinate(Math.PI,Math.PI/4, 900);
        Assert.assertEquals(1.962456, c1.getCentralAngle(sphericCoordinate), epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistance_Exception() {
            c1.getCentralAngle(isNull);
        }

    }


