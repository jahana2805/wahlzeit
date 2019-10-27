package org.wahlzeit.model;

public class Coordinate {
    private double x;
    private double y;
    private double z;
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
    public double getDistance(Coordinate coordinate){

        double distance =  Math.sqrt( Math.pow(this.getX() - coordinate.getX(), 2) + Math.pow( this.getY() - coordinate.getY(), 2) + Math.pow(this.getZ() - coordinate.getZ(), 2));
        return distance;
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
    public boolean isEqual(Coordinate coordinate){
        if (coordinate.getX() == this.x && coordinate.getY() == this.y && coordinate.getZ() == this.z){
            return true;
        }else {
            return false;
        }
    }
}
