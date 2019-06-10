package com.lentimosystems.licio.safariguides.Models;

public class Rate {
    private String rates;
    private String comment;
    private String name;

    public Rate() {
    }

    public Rate(String rates, String comment, String name) {
        this.rates = rates;
        this.comment = comment;
        this.name = name;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
