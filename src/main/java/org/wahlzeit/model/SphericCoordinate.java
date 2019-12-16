package org.wahlzeit.model;

import java.util.HashMap;

/**
 * Spheric coordinate represents a coordinate system for three-dimensional space where the position of
 * a point is specified by three numbers: phi, theta, radius
 */
public final class SphericCoordinate extends AbstractCoordinate {
    public final double phi;
    public final double theta;
    public final double radius;
    private static final double epsilon = 1e-4;
    private static HashMap<Integer, SphericCoordinate> instances = new HashMap<>();
    /**
     * constructor
     *
     * @param phi
     * @param theta
     * @param radius
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        super();
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        assertClassInvariants();
    }
    /**
     * Objects are saved on a Hashmap
     *
     * @param phi
     * @param theta
     * @param radius
     * @return a value object with indicated values
     */
    public final static SphericCoordinate getInstance(double phi, double theta, double radius) {
        final int hashed = hashHelper(phi, theta, radius);
        if (instances.get(hashed) == null) {
            synchronized (SphericCoordinate.class) {
                if (instances.get(hashed) == null) {
                    instances.put(hashed, new SphericCoordinate(phi, theta, radius));
                }
            }
        }
        return instances.get(hashed);
    }
    /**
     * Get phi value.
     *
     * @return phi value of coordinate.
     */
    public double getPhi() {
        return phi;
    }
    /**
     * Get theta value.
     *
     * @return theta value of coordinate.
     */
    public double getTheta() {
        return theta;
    }
    /**
     * Get radius value.
     *
     * @return radius value of coordinate.
     */
    public double getRadius() {
        return radius;
    }
    /**
     * Converts Spheric to Cartesian Coordinate
     *
     * @return converted cartesian coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        final double x = this.getRadius() * Math.sin(this.getTheta()) * Math.cos(this.getPhi());
        final double y = this.getRadius() * Math.sin(this.getTheta()) * Math.sin(this.getPhi());
        final double z = this.getRadius() * Math.cos(this.getTheta());
        assertClassInvariants();
        return CartesianCoordinate.getInstance(x, y, z);
    }
    /**
     * Overridden method, converts to Spheric Coordinate
     *
     * @return this
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }
    /**
     * Asserts that phi, theta, radius are finite numbers and corresponds to the restricted ranges,
     * according to the geometrical terms
     *
     */
    @Override
    protected void assertClassInvariants() {
        assert Double.isFinite(radius);
        assert radius > 0;
        assert Double.isFinite(phi);
        assert phi >= 0;
        assert phi <= 360.0;
        assert Double.isFinite(theta);
        assert theta >= 0;
        assert theta <= 180.0;
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
        SphericCoordinate turnedToSpheric = coordinate.asSphericCoordinate();
           final boolean eqPhi = Math.abs(this.getPhi() - turnedToSpheric.getPhi()) < epsilon;
           final boolean eqTheta = Math.abs(this.getTheta() - turnedToSpheric.getTheta()) < epsilon;
           final boolean eqRadius = Math.abs(this.getRadius() - turnedToSpheric.getRadius()) < epsilon;
        assertClassInvariants();
            return eqPhi && eqTheta && eqRadius;
        }

    /**
     * Has to be implemented when equals is overridden.
     *
     * calls hashHelper
     * @return hash code value of value objects
     */
    @Override
    public int hashCode() {
        return hashHelper(phi, theta, radius);
    }
    /**
     * Helper method.
     * @param phi
     * @param theta
     * @param radius
     * @return hash code value
     */
        public static int hashHelper(double phi, double theta, double radius){
            final int prime = 31;
            int result = 1;
            long temp;
            temp = Double.doubleToLongBits(phi);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(theta);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(radius);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }

