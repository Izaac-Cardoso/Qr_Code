package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PortoBelem extends ResponseType {
    @Id
    private String id = "PB";
    private double pH;
    private boolean ecoli;
    private String sampleDate;

    public PortoBelem(double pH, boolean ecoli, String date) {
        this.pH = pH;
        this.ecoli = ecoli;
        this.sampleDate = date;
    }

//check the best type to handle date type, for now it is set as String
    public String getId() {
        return id;
    }

    public double getpH() {
        return pH;
    }

    public boolean isEcoli() {
        return ecoli;
    }

    public String getSampleDate() {
        return sampleDate;
    }
}
