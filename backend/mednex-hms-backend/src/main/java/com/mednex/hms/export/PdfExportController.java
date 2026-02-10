package com.mednex.hms.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class PdfExportController {

	@Autowired
	 private  PatientPdfService service;

	    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	    public byte[] export(@PathVariable String id) throws Exception {
	        return service.generate(id);
	    }
	    
	    @PostMapping("/patient-history")
	    public ResponseEntity<String> exportEncryptedPdf() {

	        // 🔐 Simulated encryption
	        // In real world → password protected PDF using patient DOB
	        return ResponseEntity.ok(
	            "Patient history exported as encrypted PDF"
	        );
	    }
}
