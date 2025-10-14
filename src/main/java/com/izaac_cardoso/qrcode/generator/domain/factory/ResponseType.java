package com.izaac_cardoso.qrcode.generator.domain.factory;

import java.util.HashMap;

public class ResponseType {

    private String type;

    private HashMap<String, String> fieldMap;

    public String getType() {
        return type;
    }

    public HashMap<String, String> getFields() {
        return fieldMap;
    }
}
