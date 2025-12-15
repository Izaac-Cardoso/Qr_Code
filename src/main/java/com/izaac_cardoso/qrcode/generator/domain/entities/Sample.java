package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Sample implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Id
    @NotNull
    private String id;

//Look up for the right data type to deal with date and how to format it correctly ('dd/MM/yyyy' -> 'yyyy-MM-dd')
    @NotNull
    @Column(name = "collect_date")
    @Temporal(TemporalType.DATE)
    private String date;

    @Column(columnDefinition = "json")
    private String fields;

    public Sample() {}

    public Sample(String id, String date, String fields) {
        this.id = id;
        this.date = date;
        this.fields = fields;
    }

    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public String getFields() {
        return fields;
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
