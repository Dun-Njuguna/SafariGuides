package com.lentimosystems.licio.safariguides.Models;

public class VansItem {
    public String car_image;
    public String driver;
    public String driver_image;
    public String number_plate;
    public String rates;

    public VansItem() {
    }

    public VansItem(String car_image, String driver, String driver_image, String number_plate, String rates) {
        this.car_image = car_image;
        this.driver = driver;
        this.driver_image = driver_image;
        this.number_plate = number_plate;
        this.rates = rates;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriver_image() {
        return driver_image;
    }

    public void setDriver_image(String driver_image) {
        this.driver_image = driver_image;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public void setNumber_plate(String number_plate) {
        this.number_plate = number_plate;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }
}
