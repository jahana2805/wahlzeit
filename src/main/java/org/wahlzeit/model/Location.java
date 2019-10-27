package org.wahlzeit.model;

public class Location {
    public Coordinate coordinate;

    public Location(Coordinate coordinate){
        if (coordinate != null){
            this.coordinate = coordinate;
        } else{
            throw new IllegalArgumentException("Coordinate should have a value!");
        }
    }
}
