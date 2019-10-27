package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    double epsilon =1e-4;
    @Test
    public void testGetDistance__equalC() {
        Coordinate c1 = new Coordinate(1.1, 2.5, 5.5);
        Coordinate c2 = new Coordinate(1.1, 2.5, 5.5);
        double actual = c1.getDistance(c2);
        Assert.assertEquals("Wrong", 0, actual, epsilon);
    }

    @Test
    public void testGetDistance__not_equalC() {
        Coordinate c1 = new Coordinate(1.1, 2.5, 5.5);
        Coordinate c2 = new Coordinate(2.9, 3.9, 9.9);
        double actual = c1.getDistance(c2);
        Assert.assertEquals("Wrong", 4.9558, actual, epsilon);
    }

    @Test
    public void testIsEqual(){
        Coordinate c1 = new Coordinate(1.1,3,5);
        Coordinate c2 = new Coordinate(1.1,3,5);
        Coordinate c3 = new Coordinate(2.2,3,5);
        Coordinate c4 = new Coordinate(1.1,3.3,5);
        Coordinate c5 = new Coordinate(1.1,3,5.5);
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(c4));
        assertFalse(c1.equals(c5));
    }
    @Test
    public void testEquals__diffObj(){
        Coordinate c1 = new Coordinate(1.1,3,5);
        Object o = "abc";
        assertFalse(c1.equals(o));}

    @Test
    public void testEquals__someObj(){
        Coordinate c1 = new Coordinate(1.1,3,5);
        Object o1 = new Object();
        assertFalse(c1.equals(o1));}
    @Test
    public void testEquals__Same(){
        Coordinate c1 = new Coordinate(1.1,3,5);
        Coordinate c2 = new Coordinate(1.1,3,5);
        assertTrue(c1.equals(c2));}
    @Test
    public void testEquals__Diff(){
        Coordinate c1 = new Coordinate(1.1,3,5);
        Coordinate c3 = new Coordinate(2.2,3,5);
        assertFalse(c1.equals(c3));
    }

}