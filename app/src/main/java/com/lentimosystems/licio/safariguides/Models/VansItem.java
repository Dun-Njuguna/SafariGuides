package com.lentimosystems.licio.safariguides.Models;

public class VansItem {
    public String car_image;
    public String driver;
    public String driver_image;
    public String number_plate;

    public VansItem() {
    }

    public VansItem(String car_image, String driver, String driver_image, String number_plate) {
        this.car_image = car_image;
        this.driver = driver;
        this.driver_image = driver_image;
        this.number_plate = number_plate;
    }

    public String getCarImage() {
        return car_image;
    }

    public void setCarImage(String car_image) {
        this.car_image = car_image;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverImage() {
        return driver_image;
    }

    public void setDriverImage(String driver_image) {
        this.driver_image = driver_image;
    }

    public String getNumberPlate() {
        return number_plate;
    }

    public void setNumberPlate(String number_plate) {
        this.number_plate = number_plate;
    }
}
