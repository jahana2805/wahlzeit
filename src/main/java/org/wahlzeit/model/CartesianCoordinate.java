package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate {
    private double x;
    private double y;
    private double z;
    private static final double epsilon = 1e-4;

    public CartesianCoordinate(double x, double y, double z) {
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
    public double getCartesianDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        }else {
            CartesianCoordinate turnedToCartCoord = coordinate.asCartesianCoordinate();
            double distance = Math.sqrt(Math.pow(Math.abs(this.getX() - turnedToCartCoord.getX()), 2) + Math.pow(Math.abs(this.getY() - turnedToCartCoord.getY()), 2) + Math.pow(Math.abs(this.getZ() - turnedToCartCoord.getZ()), 2));
            return distance;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){ return false;
        } else if (!(obj instanceof Coordinate)){ return false;
        }else {
            Coordinate c = (Coordinate) obj;
            return isEqual(c);
        }
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2)+Math.pow(this.getZ(), 2));
        double theta = Math.acos(this.getZ()/radius);
        double phi = Math.atan(this.getY()/this.getX());
        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        } else {
        return this.asSphericCoordinate().getCentralAngle(coordinate);
    }}
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

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        } else {
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
    }

    }
