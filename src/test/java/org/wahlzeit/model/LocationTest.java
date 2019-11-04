package org.wahlzeit.model;

import org.junit.Test;

public class LocationTest {
    Coordinate c = new Coordinate(1.1, 2.2, 3.3);
    Coordinate isNull;
    @Test
    public void location__setCoordinate() {
        new Location(c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowException() {
        new Location(isNull);
    }

}
