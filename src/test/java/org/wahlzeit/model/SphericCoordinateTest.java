package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {
    double epsilon = 1e-4;
    SphericCoordinate isNull;
    SphericCoordinate s1;
    SphericCoordinate s2;
    SphericCoordinate s3;

    @Before
    public void setup() {
        s1 = new SphericCoordinate(180.0,45.0,900);
        s2 = new SphericCoordinate(180.0,45.0,900);
        s3 = new SphericCoordinate(90.0,90.0,1000);
        isNull = null;
    }
    @Test
    public void testGetCentralAngle() {
       /* double delta_phi = Math.abs(s1.phi - s3.getPhi());
        double delta_theta = Math.abs(s1.theta - s3.getTheta());
        double expected = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(delta_phi / 2), 2) + Math.cos(s1.phi) * Math.cos(s3.getPhi()) * Math.pow(Math.sin(delta_theta / 2), 2)));*/
        Assert.assertEquals(2.18384117, s1.getCentralAngle(s3), epsilon);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngle_Exception() {
    s1.getCentralAngle(isNull);
    }
    @Test
    public void testAsSphericCoordinate() {
        Assert.assertSame(s1, s1.asSphericCoordinate());
    }
    @Test
    public void testIsEqual(){
        SphericCoordinate s4 = new SphericCoordinate(180.0, Double.NaN, 900);
        SphericCoordinate s5 = new SphericCoordinate(Double.NaN, 45.0,900);
        SphericCoordinate s6 = new SphericCoordinate(180.0, 45.0, Double.NaN);
        assertTrue(s1.isEqual(s2));
        assertFalse(s1.isEqual(s3));
        assertFalse(s4.isEqual(s1));
        assertFalse(s5.isEqual(s1));
        assertFalse(s6.isEqual(s1));
        assertFalse(s1.isEqual(isNull));
    }
    @Test
    public void testEquals__diffObj(){
        Object o = "abc";
        assertFalse(s1.equals(o));}

    @Test
    public void testEquals__Obj(){
        Object o1 = new Object();
        assertFalse(s1.equals(o1));
        assertFalse(s1.hashCode() == o1.hashCode());}
    @Test
    public void testEquals__Same(){
        assertTrue(s1.equals(s2));
        assertTrue(s1.hashCode() == s2.hashCode());}
    @Test
    public void testEquals__Diff(){
        assertFalse(s1.equals(s3));
    }
    @Test
    public void testAsCartesianCoordinate() {
        CartesianCoordinate cartesianCoordinate = s1.asCartesianCoordinate();
        assertTrue(cartesianCoordinate instanceof CartesianCoordinate);
        double x = s1.getRadius() * Math.sin(s1.getTheta()) * Math.cos(s1.getPhi());
        double y = s1.getRadius() * Math.sin(s1.getTheta()) * Math.sin(s1.getPhi());
        double z = s1.getRadius() * Math.cos(s1.getTheta());
        Assert.assertEquals(-458.3086, cartesianCoordinate.getX(), epsilon);
        Assert.assertEquals(-613.5332, cartesianCoordinate.getY(), epsilon);
        Assert.assertEquals(472.78979, cartesianCoordinate.getZ(), epsilon);
    }
    @Test
    public void testGetCartesianDistance() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1.1, 2.5, 5.5);
        Assert.assertEquals(899.39586, s1.getCartesianDistance(cartesianCoordinate), epsilon);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistance_Exception() {
        s1.getCartesianDistance(isNull);
    }
}
