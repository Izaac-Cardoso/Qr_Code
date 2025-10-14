package com.izaac_cardoso.qrcode.generator.domain.entities;

import com.izaac_cardoso.qrcode.generator.domain.factory.ResponseType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;

@Entity
public class CollectedSample {

    @Id
    private String type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private HashMap<String, String> fields;

    // Construtor default é necessário para o JPA funcionar. Colocamos aqui valores padrão arbitrários
    public CollectedSample() {
        this.type = "";
        this.fields = new HashMap<String, String>();
    }

    public CollectedSample(ResponseType responseType) {
        this.type = responseType.getType();
        this.fields = responseType.getFields();
    }

    public HashMap<String, String> getFields() {
        return this.fields;
    }
}
