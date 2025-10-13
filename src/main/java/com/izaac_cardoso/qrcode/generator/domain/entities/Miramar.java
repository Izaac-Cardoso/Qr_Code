package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;
import jakarta.persistence.Entity;

@Entity
public class Miramar extends ResponseType {

    private String id = "TPQM";
    private double turbidity;
    private double phosphorus;
    private double nitrite;
    private String sampleDate;

    public Miramar(double turbidity, double phosphorus, double nitrite, String date) {
        this.turbidity = turbidity;
        this.phosphorus = phosphorus;
        this.nitrite = nitrite;
        this.sampleDate = date;
    }

    public String getId() {
        return id;
    }

    public double getTurbidity() {
        return turbidity;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public double getNitrite() {
        return nitrite;
    }

    public String getSampleDate() {
        return sampleDate;
    }
}
