package com.izaac_cardoso.qrcode.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

@Configuration
public class ClassConfig {

    @Bean
    public HttpMessageConverter<BufferedImage> qrCodeImageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
