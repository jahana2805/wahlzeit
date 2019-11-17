package org.wahlzeit.model;

import java.util.Objects;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate();
    double getCartesianDistance(Coordinate coordinate);
    SphericCoordinate asSphericCoordinate();
    double getCentralAngle(Coordinate coordinate);
    boolean isEqual(Coordinate coordinate);

}
