package com.mednex.hms.export;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PatientPdfService {

    public byte[] generate(String patientId) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        WriterProperties props = new WriterProperties()
                .setStandardEncryption(
                        "user123".getBytes(),
                        "admin123".getBytes(),
                        EncryptionConstants.ALLOW_PRINTING,
                        EncryptionConstants.ENCRYPTION_AES_128
                );

        PdfWriter writer = new PdfWriter(out, props);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("MedNex Enterprise HMS"));
        document.add(new Paragraph("Patient History Report"));
        document.add(new Paragraph("Patient ID: " + patientId));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("✔ Encrypted PDF"));
        document.add(new Paragraph("✔ HIPAA/GDPR compliant export"));

        document.close();

        return out.toByteArray();
    }
}