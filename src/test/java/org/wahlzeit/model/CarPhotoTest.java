package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CarPhotoTest {
    CarPhoto carPhoto = new CarPhoto(new PhotoId(123456),"Ferrari", "Enzo", "red");
    @Test
    public void testCarPhoto() {
        Assert.assertEquals("Ferrari", carPhoto.getBrand());
        Assert.assertEquals("Enzo", carPhoto.getModel());
        Assert.assertEquals("red", carPhoto.getColor());
        Assert.assertEquals(123456, carPhoto.getId().asInt());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testBrandIsNullException() {
        CarPhoto carBrandNull = new CarPhoto(null, "Enzo", "red");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testBrandIsEmptyException() {
        CarPhoto carBrandEmpty = new CarPhoto("", "Enzo", "red");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testModelIsNullException() {
        CarPhoto carModelNull = new CarPhoto("Ferrari", null, "red");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testModelIsEmptyException() {
        CarPhoto carModelEmpty = new CarPhoto("Ferrari", "", "red");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testColorIsNullException() {
        CarPhoto carModelNull = new CarPhoto("Ferrari", "Enzo", null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testColorIsEmptyException() {
        CarPhoto carModelEmpty = new CarPhoto("Ferrari", "Enzo", "");
    }
   }