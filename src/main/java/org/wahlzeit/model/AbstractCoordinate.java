package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

/**
 * Abstract superclass for Coordinate implementations.
 */

public abstract class AbstractCoordinate implements Coordinate {
    /**
     * Get Cartesian distance between two coordinates
     *
     * @param coordinate
     * @return Cartesian distance between @param coordinate and this
     */
    @PatternInstance(
            name = "TemplateMethod",
            participants = { "AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate" }
    )
    @Override
    public final double getCartesianDistance(Coordinate coordinate) {
        assertClassInvariants();
        assertCoordinateIsNotNull(coordinate);
            final double distance = Math.sqrt(Math.pow(Math.abs(this.asCartesianCoordinate().getX() - coordinate.asCartesianCoordinate().getX()), 2) + Math.pow(Math.abs(this.asCartesianCoordinate().getY() - coordinate.asCartesianCoordinate().getY()), 2) + Math.pow(Math.abs(this.asCartesianCoordinate().getZ() - coordinate.asCartesianCoordinate().getZ()), 2));
            assert Double.isFinite(distance);
            assert distance >= 0;
            assertClassInvariants();
            return distance;
        }
    /**
     * Checks if the object is null, throws an IllegalArgumentException
     *
     * @param o
     * @exception IllegalArgumentException
     */
    private void assertCoordinateIsNotNull(Object o){
        if(o == null) {
            throw new IllegalArgumentException("Object shouldn't be null!");
        }
    }
    /**
     * Abstract method, checks the invariants
     */
    protected abstract void assertClassInvariants();

    /**
     * Get Central angle between two coordinates
     *
     * @param coordinate
     * @return Central angle between @param coordinate and this
     */
    @PatternInstance(
            name = "TemplateMethod",
            participants = { "AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate" }
    )
    @Override
    public final double getCentralAngle(Coordinate coordinate) {
        assertClassInvariants();
        assertCoordinateIsNotNull(coordinate);
            final double delta_phi = Math.abs(this.asSphericCoordinate().phi - coordinate.asSphericCoordinate().getPhi());
            final double delta_theta = Math.abs(this.asSphericCoordinate().theta - coordinate.asSphericCoordinate().getTheta());
            final double angle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(delta_phi / 2), 2) + Math.cos(this.asSphericCoordinate().phi) * Math.cos(coordinate.asSphericCoordinate().getPhi()) * Math.pow(Math.sin(delta_theta / 2), 2)));
            assert angle > 0;
            assert angle < 2 * Math.PI;
            assert Double.isFinite(angle);
            assertClassInvariants();
                return angle;

        }
    /**
     * Checks whether obj and this have the same value, not necessarily being the same object
     * Subclasses are shared value object classes
     *
     * @param obj
     * @return is calling method doIsEqual for further equality checks if @param obj is instance of Coordinate
     */
    @Override
    public boolean equals(Object obj) {
            return this == obj;
        }

    /**
     * Compare two coordinates' values by calling method doIsEqual
     *
     * @param coordinate
     * @return true if @param coordinate and this are equal, else returns false
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        assertCoordinateIsNotNull(coordinate);
            final boolean isEqual = this.doIsEqual(coordinate);
            assertClassInvariants();
            return isEqual;
    }
    /**
     * Abstract method, where actually comparisons of two coordinates' values are performed, implemented by subclasses
     *
     * @param coordinate
     * @return
     */
    protected abstract boolean doIsEqual(Coordinate coordinate);
    /**
     * Overridden abstract method; hashcode a coordinate, implemented by subclasses
     *
     * @return hashcode of coordinate
     */
    public abstract int hashCode();
}
