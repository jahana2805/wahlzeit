package org.wahlzeit.model;
/**
 * Spheric coordinate represents a coordinate system for three-dimensional space where the position of
 * a point is specified by three numbers: phi, theta, radius
 */
public class SphericCoordinate extends AbstractCoordinate {
    public double phi;
    public double theta;
    public double radius;
    private static final double epsilon = 1e-4;
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
        double x = this.getRadius() * Math.sin(this.getTheta()) * Math.cos(this.getPhi());
        double y = this.getRadius() * Math.sin(this.getTheta()) * Math.sin(this.getPhi());
        double z = this.getRadius() * Math.cos(this.getTheta());
        assertClassInvariants();
        return new CartesianCoordinate(x, y, z);
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
            boolean eqPhi = Math.abs(this.getPhi() - turnedToSpheric.getPhi()) < epsilon;
            boolean eqTheta = Math.abs(this.getTheta() - turnedToSpheric.getTheta()) < epsilon;
            boolean eqRadius = Math.abs(this.getRadius() - turnedToSpheric.getRadius()) < epsilon;
        assertClassInvariants();
            return eqPhi && eqTheta && eqRadius;
        }

    /**
     * Has to be implemented when equals is overridden.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
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

