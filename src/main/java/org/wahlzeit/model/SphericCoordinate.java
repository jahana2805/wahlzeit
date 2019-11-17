package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
    public double phi;
    public double theta;
    public double radius;
    private static final double epsilon = 1e-4;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.getRadius() * Math.sin(this.getTheta()) * Math.cos(this.getPhi());
        double y = this.getRadius() * Math.sin(this.getTheta()) * Math.sin(this.getPhi());
        double z = this.getRadius() * Math.cos(this.getTheta());
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        } else {
            return this.asCartesianCoordinate().getCartesianDistance(coordinate);
        }
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate should have a value!");
        } else {
            double delta_phi = Math.abs(this.phi - coordinate.asSphericCoordinate().getPhi());
            double delta_theta = Math.abs(this.theta - coordinate.asSphericCoordinate().getTheta());
            double angle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(delta_phi / 2), 2) + Math.cos(this.phi) * Math.cos(coordinate.asSphericCoordinate().getPhi()) * Math.pow(Math.sin(delta_theta / 2), 2)));
            return angle;
        }
    }
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
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        } else {
            SphericCoordinate turnedToSpheric = coordinate.asSphericCoordinate();
            if (Double.isNaN(this.phi) || Double.isNaN(turnedToSpheric.getPhi()) || Double.isNaN(this.theta) || Double.isNaN(turnedToSpheric.getTheta()) || Double.isNaN(this.radius) || Double.isNaN(turnedToSpheric.getRadius())) {
                return false;
            } else {
                boolean eqPhi = Math.abs(this.getPhi() - turnedToSpheric.getPhi()) < epsilon;
                boolean eqTheta = Math.abs(this.getTheta() - turnedToSpheric.getTheta()) < epsilon;
                boolean eqRadius = Math.abs(this.getRadius() - turnedToSpheric.getRadius()) < epsilon;
                return eqPhi && eqTheta && eqRadius;
            }
        }
    }
}