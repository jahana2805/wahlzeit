package org.wahlzeit.model;

import java.util.Objects;

public class Coordinate {
    private double x;
    private double y;
    private double z;

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

    public Coordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    protected double getDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        } else {
            double distance = Math.sqrt(Math.pow(this.getX() - coordinate.getX(), 2) + Math.pow(this.getY() - coordinate.getY(), 2) + Math.pow(this.getZ() - coordinate.getZ(), 2));
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
    private static final double epsilon = 1e-4;
    protected boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        } else {
            if (Double.isNaN(this.x) || Double.isNaN(coordinate.getX()) || Double.isNaN(this.y) || Double.isNaN(coordinate.getY()) || Double.isNaN(this.z) || Double.isNaN(coordinate.getZ())) {
                return false;
            } else {
                boolean eqX = Math.abs(this.getX() - coordinate.getX()) < epsilon;
                boolean eqY = Math.abs(this.getY() - coordinate.getY()) < epsilon;
                boolean eqZ = Math.abs(this.getZ() - coordinate.getZ()) < epsilon;
                return eqX && eqY && eqZ;

            }
        }
    }
}
