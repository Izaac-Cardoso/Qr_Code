package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashMap;

public class PortoBelem extends ResponseType {
    private String type = "PB";
    private double pH;
    private boolean ecoli;
    private String sampleDate;

    public PortoBelem(double pH, boolean ecoli, String date) {
        this.pH = pH;
        this.ecoli = ecoli;
        this.sampleDate = date;
    }

    public PortoBelem(HashMap<String, String> fieldMap) {
        this.pH = Double.parseDouble(fieldMap.get("pH"));
        this.ecoli = Boolean.parseBoolean(fieldMap.get("ecoli"));
        this.sampleDate = fieldMap.get("sampleDate");
    }

    //check the best type to handle date type, for now it is set as String
    public String getType() {
        return type;
    }

    public HashMap<String, String> getFields() {
        HashMap<String, String> fieldMap = new HashMap<String, String>();

        fieldMap.put("pH", String.valueOf(this.pH));
        fieldMap.put("ecoli", String.valueOf(this.ecoli));
        fieldMap.put("sampleDate", String.valueOf(this.sampleDate));

        return fieldMap;
    }

}
