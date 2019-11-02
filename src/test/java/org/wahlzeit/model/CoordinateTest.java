package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    double epsilon =1e-4;
    Coordinate isNull;
    Coordinate c1 = new Coordinate(1.1,2.5,5.5);
    @Test
    public void testGetDistance__equalC() {
        Coordinate c2 = new Coordinate(1.1, 2.5, 5.5);
        double actual = c1.getDistance(c2);
        Assert.assertEquals("Wrong", 0, actual, epsilon);
    }

    @Test
    public void testGetDistance__not_equalC() {
        Coordinate c2 = new Coordinate(2.9, 3.9, 9.9);
        double actual = c1.getDistance(c2);
        Assert.assertEquals("Wrong", 4.9558, actual, epsilon);
    }

    @Test
    public void testIsEqual(){
        Coordinate c2 = new Coordinate(1.1,2.5,5.5);
        Coordinate c3 = new Coordinate(2.2,3,5);
        Coordinate c4 = new Coordinate(1.1,3.3,5);
        Coordinate c5 = new Coordinate(1.1,3,5.5);
        Coordinate c6 = new Coordinate(5.5, Double.NaN, 2);
        Coordinate c7 = new Coordinate(Double.NaN, 1,2);
        Coordinate c8 = new Coordinate(4.2, 5.5, Double.NaN);
        assertTrue(c1.isEqual(c2));
        assertFalse(c1.isEqual(c3));
        assertFalse(c1.isEqual(c4));
        assertFalse(c1.isEqual(c5));
        assertFalse(c6.isEqual(c1));
        assertFalse(c7.isEqual(c2));
        assertFalse(c8.isEqual(c3));
        assertFalse(c1.isEqual(isNull));
    }
    @Test
    public void testEquals__diffObj(){
        Object o = "abc";
        assertFalse(c1.equals(o));}

    @Test
    public void testEquals__someObj(){
        Object o1 = new Object();
        assertFalse(c1.equals(o1));
        assertFalse(c1.hashCode() == o1.hashCode());}
    @Test
    public void testEquals__Same(){
        Coordinate c2 = new Coordinate(1.1,2.5,5.5);
        assertTrue(c1.equals(c2));
        assertTrue(c1.hashCode() == c2.hashCode());}
    @Test
    public void testEquals__Diff(){
        Coordinate c3 = new Coordinate(2.2,3,5);
        assertFalse(c1.equals(c3));
    }
    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowException() {
    c1.getDistance(isNull);
    }

}