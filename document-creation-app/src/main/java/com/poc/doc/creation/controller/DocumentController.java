package com.poc.doc.creation.controller;


import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.doc.creation.model.DocumentRequest;
import com.poc.doc.creation.service.DocumentService;

@RestController
@RequestMapping(path = "/api/poc/document/v1")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;	
	
/**
 * 	
 * @param documentRequest
 * @return pdf stream
 */
@PostMapping(path = { "/create" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)	
public @ResponseBody ResponseEntity<InputStreamResource> getRenewalDocument(@RequestBody DocumentRequest documentRequest) {
   
	String fileName = documentService.createTaxPDF(documentRequest);
	FileInputStream fileStream= documentService.getGeneratedFile(fileName);
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=taxpdf.pdf");
	return ResponseEntity
	        .ok()
	        .headers(headers)
	        .contentType(MediaType.APPLICATION_PDF)
	        .body(new InputStreamResource(fileStream));

}

	
}