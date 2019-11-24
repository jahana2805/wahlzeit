package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
    public double phi;
    public double theta;
    public double radius;
    private static final double epsilon = 1e-4;

    public SphericCoordinate(double phi, double theta, double radius) {
        super();
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
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }



    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
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

