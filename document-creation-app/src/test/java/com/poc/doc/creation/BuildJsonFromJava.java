package com.poc.doc.creation;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.poc.doc.creation.model.DocumentRequest;

public class BuildJsonFromJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DocumentRequest request = new  DocumentRequest();
		Map <String, String> docData = new HashMap<String, String>();
		docData.put("year", "2007");
		docData.put("recipient", "3");
		docData.put("country_code", "B H S");
		docData.put("payer_identification", "14143983");
		docData.put("ca_tax_identification", "011027229");
		request.setClient("slf");
		request.setYear("2007");
		request.setDocName("NH4");
		request.setDocData(docData);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json ="";
		try {
			json = ow.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);

	}

}
