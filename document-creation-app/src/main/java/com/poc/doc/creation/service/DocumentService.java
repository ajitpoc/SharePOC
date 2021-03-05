package com.poc.doc.creation.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poc.doc.creation.model.DocumentRequest;

@Service
public class DocumentService {
	
	@Value(value ="${tax.template.basedir}")
	private String templateBaseDir;
	
	
	public String createTaxPDF(DocumentRequest request) {
		PDDocument pDDocument = null;
		String createdfileName = null;
		try {
			 pDDocument = PDDocument.load(new File(templateBaseDir + request.getDocName()+"_template.pdf"));
			 PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
			//Data Map 
			/*  request.getDocData().entrySet().stream()
		       .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
		       */
			
			  request.getDocData().entrySet().stream()
		       .forEach(e ->  { PDField field = pDAcroForm.getField(e.getKey());
		         try {
					field.setValue(e.getValue());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		       						
		       });
			  
			 pDDocument.setAllSecurityToBeRemoved(true);
			 createdfileName = templateBaseDir + request.getDocName()+"_out.pdf";
			 pDDocument.save(createdfileName);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pDDocument != null )
				try {
					pDDocument.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return createdfileName;
		
	}
	
	public FileInputStream  getGeneratedFile(String createdfileName)  {
		
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(createdfileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return fin;
	}

}
