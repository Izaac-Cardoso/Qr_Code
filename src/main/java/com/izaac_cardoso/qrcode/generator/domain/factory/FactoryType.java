package com.izaac_cardoso.qrcode.generator.domain.factory;

import com.izaac_cardoso.qrcode.generator.domain.entities.Miramar;
import com.izaac_cardoso.qrcode.generator.domain.entities.Outeiro;
import com.izaac_cardoso.qrcode.generator.domain.entities.PortoBelem;
import com.izaac_cardoso.qrcode.generator.dtos.DTOResponse;

import java.util.Map;

public class FactoryType {

    public static ResponseType getSampleType(DTOResponse response) {

//import ModelMapper library to map dto into entities
        Map<String, ResponseType> sample = Map.of(
                "PB", new PortoBelem(response.pH(), response.ecoli(), response.date()),
                "TPQM", new Miramar(response.turbidity(), response.phosphorus(), response.nitrite(), response.date()),
                "OUT", new Outeiro(response.color(), response.chlorine(), response.tds(), response.date())
        );

        return sample.get(response.id());
    }
}
