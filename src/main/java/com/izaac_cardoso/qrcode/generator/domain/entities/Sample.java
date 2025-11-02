package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class Sample implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Id
    @NotNull
    private String id;

    @NotNull
    private String date;

    @Column(columnDefinition = "json")
    private String fields;

    public Sample() {}

    public Sample(String id, String date, String fields) {
        this.id = id;
        this.date = date;
        this.fields = fields;
    }

    public String id() {
        return this.id;
    }

    public String date() {
        return this.date;
    }

    //Serialize the entity as json
    private String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonProcessingException e) {
            return "id : " + id +
                    "\ndate : " + date +
                    "\nfields : " + fields;
        }
    }
}
