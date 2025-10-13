package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Outeiro extends ResponseType {

    @Id
    private String id = "OUT";
    private double color;
    private double chlorine;
    private double tds;
    private String sampleDate;

    public Outeiro(double color, double chlorine, double tds, String sampleDate) {
        this.color = color;
        this.chlorine = chlorine;
        this.tds = tds;
        this.sampleDate = sampleDate;
    }

    public String getId() {
        return id;
    }

    public double getColor() {
        return color;
    }

    public double getChlorine() {
        return chlorine;
    }

    public double getTds() {
        return tds;
    }

    public String getSampleDate() {
        return sampleDate;
    }
}
