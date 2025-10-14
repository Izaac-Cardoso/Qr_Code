package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;

import java.util.HashMap;

public class Miramar extends ResponseType {

    private String type = "TPQM";
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

    public Miramar(HashMap<String, String> fieldMap) {
        this.turbidity = Double.parseDouble(fieldMap.get("turbidity"));
        this.phosphorus = Double.parseDouble(fieldMap.get("phosphorus"));
        this.nitrite = Double.parseDouble(fieldMap.get("nitrite"));
        this.sampleDate = fieldMap.get("sampleDate");
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getFields() {
        HashMap<String, String> fieldMap = new HashMap<String, String>();

        fieldMap.put("turbidity", String.valueOf(this.turbidity));
        fieldMap.put("phosphorus", String.valueOf(this.phosphorus));
        fieldMap.put("nitrite", String.valueOf(this.nitrite));
        fieldMap.put("sampleDate", String.valueOf(this.sampleDate));

        return fieldMap;
    }
}
