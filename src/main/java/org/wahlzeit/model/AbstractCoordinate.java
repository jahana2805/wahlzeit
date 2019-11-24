package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        } else {
            double distance = Math.sqrt(Math.pow(Math.abs(this.asCartesianCoordinate().getX() - coordinate.asCartesianCoordinate().getX()), 2) + Math.pow(Math.abs(this.asCartesianCoordinate().getY() - coordinate.asCartesianCoordinate().getY()), 2) + Math.pow(Math.abs(this.asCartesianCoordinate().getZ() - coordinate.asCartesianCoordinate().getZ()), 2));
            return distance;
        }
    }


    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        } else {
            double delta_phi = Math.abs(this.asSphericCoordinate().phi - coordinate.asSphericCoordinate().getPhi());
            double delta_theta = Math.abs(this.asSphericCoordinate().theta - coordinate.asSphericCoordinate().getTheta());
            double angle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(delta_phi / 2), 2) + Math.cos(this.asSphericCoordinate().phi) * Math.cos(coordinate.asSphericCoordinate().getPhi()) * Math.pow(Math.sin(delta_theta / 2), 2)));
            if (angle > 0 && angle < 2 * Math.PI) {
                return angle;
            } else {
                throw new IllegalArgumentException("Angle should be more than 0° and less than 360°!");
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Coordinate)) {
            return false;
        } else {
            Coordinate c = (Coordinate) obj;
            return isEqual(c);
        }
    }


    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        } else {
            return this.doIsEqual(coordinate);
        }
    }
    protected abstract boolean doIsEqual(Coordinate coordinate);
    public abstract int hashCode();
}
