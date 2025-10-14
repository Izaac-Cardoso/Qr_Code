package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashMap;

public class Outeiro extends ResponseType {

    private String type = "OUT";
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

    public Outeiro(HashMap<String, String> fieldMap) {
        this.color = Double.parseDouble(fieldMap.get("color"));
        this.chlorine = Double.parseDouble(fieldMap.get("chlorine"));
        this.tds = Double.parseDouble(fieldMap.get("tds"));
        this.sampleDate = fieldMap.get("sampleDate");
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getFields() {
        HashMap<String, String> fieldMap = new HashMap<String, String>();

        fieldMap.put("color", String.valueOf(this.color));
        fieldMap.put("chlorine", String.valueOf(this.chlorine));
        fieldMap.put("tds", String.valueOf(this.tds));
        fieldMap.put("sampleDate", String.valueOf(this.sampleDate));

        return fieldMap;
    }

}
