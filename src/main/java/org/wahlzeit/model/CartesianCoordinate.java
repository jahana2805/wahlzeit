package org.wahlzeit.model;
/**
 * Cartesian coordinate represents a coordinate system that specifies each point
 * uniquely in a plane by a set of points: x, y, z
 */
public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;
    private static final double epsilon = 1e-4;
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
        double radius = Math.sqrt(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2)+Math.pow(this.getZ(), 2));
        double theta = Math.acos(this.getZ()/radius);
        double phi = Math.atan(this.getY()/this.getX());
        assertClassInvariants();
        return new SphericCoordinate(phi, theta, radius);
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
            boolean eqX = Math.abs(this.getX() - turnedToCartCoord.getX()) < epsilon;
            boolean eqY = Math.abs(this.getY() - turnedToCartCoord.getY()) < epsilon;
            boolean eqZ = Math.abs(this.getZ() - turnedToCartCoord.getZ()) < epsilon;
            assertClassInvariants();
            return eqX && eqY && eqZ;
        }


    /**
     * Has to be implemented when equals is overridden.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {	final int prime = 31;
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

    }
