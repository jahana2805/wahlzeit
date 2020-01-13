package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

import java.util.HashMap;

/**
 * Cartesian coordinate represents a coordinate system that specifies each point
 * uniquely in a plane by a set of points: x, y, z
 */

public final class CartesianCoordinate extends AbstractCoordinate {
    private final double x;
    private final double y;
    private final double z;
    private static final double epsilon = 1e-4;
    private static HashMap<Integer, CartesianCoordinate> instances = new HashMap<>();
    /**
     * constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public CartesianCoordinate(double x, double y, double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }
    /**
     * Objects are saved on a Hashmap
     *
     * @param x
     * @param y
     * @param z
     * @return a value object with indicated values
     */
    @PatternInstance(
            name = "Singleton", participants = { "CartesianCoordinate"}
    )
    public final static CartesianCoordinate getInstance(double x, double y, double z) {
        final int hashed = hashHelper(x,y,z);
        if (instances.get(hashed) == null) {
            synchronized (CartesianCoordinate.class) {
                if (instances.get(hashed) == null) {
                    instances.put(hashed, new CartesianCoordinate(x, y, z));
                }
            }
        }
                return instances.get(hashed);
    }
    /**
     * Get x value.
     *
     * @return x value of coordinate.
     */
    public double getX() {
        return x;
    }
    /**
     * Get y value.
     *
     * @return y value of coordinate.
     */
    public double getY() {
        return y;
    }
    /**
     * Get z value.
     *
     * @return z value of coordinate.
     */
    public double getZ() {
        return z;
    }
    /**
     * Overridden method, converts to Cartesian Coordinate
     *
     * @return this
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }
    /**
     * Converts Cartesian to Spheric Coordinate
     *
     * @return converted spheric coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        final double radius = Math.sqrt(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2)+Math.pow(this.getZ(), 2));
        final double theta = Math.acos(this.getZ()/radius);
        final double phi = Math.atan(this.getY()/this.getX());
        assertClassInvariants();
        return SphericCoordinate.getInstance(phi, theta, radius);
    }
    /**
     * Asserts that all three points are finite numbers
     *
     */
    @Override
    protected void assertClassInvariants() {
        assert Double.isFinite(x);
        assert Double.isFinite(y);
        assert Double.isFinite(z);
    }

    /**
     * Method where actually comparisons of two coordinates' values are performed
     *
     * @param coordinate
     * @return
     */
    @Override
   protected boolean doIsEqual(Coordinate coordinate) {
        assertClassInvariants();
        CartesianCoordinate turnedToCartCoord = coordinate.asCartesianCoordinate();
           final boolean eqX = Math.abs(this.getX() - turnedToCartCoord.getX()) < epsilon;
           final boolean eqY = Math.abs(this.getY() - turnedToCartCoord.getY()) < epsilon;
           final boolean eqZ = Math.abs(this.getZ() - turnedToCartCoord.getZ()) < epsilon;
            assertClassInvariants();
            return eqX && eqY && eqZ;
        }
    /**
     * Helper method.
     * @param x
     * @param y
     * @param z
     * @return hash code value
     */
        private static int hashHelper(double x, double y, double z){
            final int prime = 31;
            int result = 1;
            long temp;
            temp = Double.doubleToLongBits(x);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(y);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(z);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

    /**
     * Has to be implemented when equals is overridden.
     *
     * calls hashHelper
     * @return hash code value of value objects
     */
    @Override
    public int hashCode() {
     return hashHelper(x,y,z);}

    }


