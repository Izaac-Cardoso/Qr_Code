package com.izaac_cardoso.qrcode.generator.dtos;


import jakarta.validation.constraints.Pattern;

public record DTORequest(
    String id,
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])'/'(0[1-9]|1[012])'/'d{4}$")
    String date,
    String message
) { }
