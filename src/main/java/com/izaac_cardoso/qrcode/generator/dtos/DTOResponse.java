package com.izaac_cardoso.qrcode.generator.dtos;

public record DTOResponse (
//insert field validation
        String id,
        String date,
        double pH,
        double tds,
        double color,
        double turbidity,
        double nitrite,
        double nitrate,
        double phosphorus,
        double chlorine,
        boolean ecoli
) { }
