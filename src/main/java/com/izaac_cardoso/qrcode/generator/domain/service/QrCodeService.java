package com.izaac_cardoso.qrcode.generator.domain.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.izaac_cardoso.qrcode.generator.domain.entities.CollectedSample;
import com.izaac_cardoso.qrcode.generator.domain.entities.Miramar;
import com.izaac_cardoso.qrcode.generator.domain.entities.Outeiro;
import com.izaac_cardoso.qrcode.generator.domain.entities.PortoBelem;
import com.izaac_cardoso.qrcode.generator.dtos.DTORequest;
import com.izaac_cardoso.qrcode.generator.repository.CollectedSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Service
public class QrCodeService {

    @Autowired
    private CollectedSampleRepository collectedSampleRepository;


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//  Implement a logic to filter what link should be delivered according to the id passed in
//    private String qRCodeFilter(String id, LocalDateTime date) {
//        Map<String, Supplier<String>> urls = Map.of(
//                "PB", () -> "http://domain.com/form?id=value&date=date",
//                "TPQM", () -> "http://domain.com/form?id=value&date=date",
//                "OUT", () -> "http://domain.com/form?id=value&date=date"
//        );
//
//        return urls.getOrDefault(id, () -> "Invalid id").get();
//    }

    private String qRCodeFilter(String id, LocalDateTime date) {
        Map<String, String> urls = Map.of(
                "PB","http://domain/form-1?id=s%&date=s%",
                "TPQM", "http://domain/form-2?id=s%&date=s%",
                "OUT", "http://domain/form-3?id=s%&date=s%"
        );

        String content = urls.get(id);
        if(content == null) {
            throw new IllegalArgumentException(id + " invalid");
        }

        return String.format(content, id, formatter.format(date));
    }

    public BufferedImage generateQRCode(DTORequest request) throws WriterException {
        if(request == null || request.id() == null || request.date() == null) {
            throw new IllegalArgumentException("Request, id and date must not be null.");
        }

        LocalDateTime formattedDate;
        try {
            formattedDate = LocalDateTime.parse(request.date(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date must be 'dd/MM/yyyy' format");
        }

        String content = qRCodeFilter(request.id(), formattedDate);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public CollectedSample createSample(String type) throws Exception {

        CollectedSample sample;

        switch (type) {
            case "TPQM":
                Miramar miramar = new Miramar(0.0, 0.0, 0.0, "2025-01-01");
                sample = new CollectedSample(miramar);
                break;
            case "OUT":
                Outeiro outeiro = new Outeiro(0.0, 0.0, 0.0, "2025-01-01");
                sample = new CollectedSample(outeiro);
                break;
            case "PB":
                PortoBelem portoBelem = new PortoBelem(0.0, true, "2025-01-01");
                sample = new CollectedSample(portoBelem);
                break;
            default:
                throw new Exception("Tu é leso é? As opções são TPQM, OUT, PB");
        }

        return collectedSampleRepository.save(sample);
    }

    public CollectedSample readSample(String type) throws Exception {

        if (collectedSampleRepository.findById(type).isPresent()) {
            CollectedSample sample = collectedSampleRepository.findById(type).get();

            // Acho feio esse formato de switch, mas tô com preguiça de voltar :-P
            sample = switch (type) {
                case "TPQM" -> {
                    Miramar miramar = new Miramar(sample.getFields());
                    yield new CollectedSample(miramar);
                }
                case "OUT" -> {
                    Outeiro outeiro = new Outeiro(sample.getFields());
                    yield new CollectedSample(outeiro);
                }
                case "PB" -> {
                    PortoBelem portoBelem = new PortoBelem(sample.getFields());
                    yield new CollectedSample(portoBelem);
                }
                default -> throw new Exception("Tu é leso é? As opções são TPQM, OUT, PB");
            };

            return sample;
        }

        throw new Exception("Esse elemento não existe!");
    }
}
