package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;
    private static final double epsilon = 1e-4;

    public CartesianCoordinate(double x, double y, double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2)+Math.pow(this.getZ(), 2));
        double theta = Math.acos(this.getZ()/radius);
        double phi = Math.atan(this.getY()/this.getX());
        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
   protected boolean doIsEqual(Coordinate coordinate) {
        CartesianCoordinate turnedToCartCoord = coordinate.asCartesianCoordinate();
        if (Double.isNaN(this.x) || Double.isNaN(turnedToCartCoord.getX()) || Double.isNaN(this.y) || Double.isNaN(turnedToCartCoord.getY()) || Double.isNaN(this.z) || Double.isNaN(turnedToCartCoord.getZ())) {
            return false;
        } else {
            boolean eqX = Math.abs(this.getX() - turnedToCartCoord.getX()) < epsilon;
            boolean eqY = Math.abs(this.getY() - turnedToCartCoord.getY()) < epsilon;
            boolean eqZ = Math.abs(this.getZ() - turnedToCartCoord.getZ()) < epsilon;
            return eqX && eqY && eqZ;
        }
    }


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
