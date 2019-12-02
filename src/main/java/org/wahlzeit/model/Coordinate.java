package org.wahlzeit.model;

public interface Coordinate {
    /**
     * Converts coordinate if Spheric to Cartesian, if it is Cartesian then it returns itself
     *
     * @return Cartesian Coordinate
     */
    CartesianCoordinate asCartesianCoordinate();
    /**
     * Get Cartesian distance between two coordinates
     *
     * @param coordinate
     * @return Cartesian distance between @param coordinate and this
     */
    double getCartesianDistance(Coordinate coordinate);
    /**
     * Converts coordinate if Cartesian to Spheric, if it is Spheric then it returns itself
     *
     * @return Spheric Coordinate
     */
    SphericCoordinate asSphericCoordinate();
    /**
     * Get Central angle between two coordinates
     *
     * @param coordinate
     * @return Central angle between @param coordinate and this
     */
    double getCentralAngle(Coordinate coordinate);
    /**
     * Compare two coordinates' values
     *
     * @param coordinate
     * @return true if @param coordinate and this are equal, else returns false
     */
    boolean isEqual(Coordinate coordinate);

}
