package com.poc.doc.creation.model;

import java.util.HashMap;
import java.util.Map;

public class DocumentRequest {
	private String docName;
	private String client;
	private String year;
	private Map <String, String> docData = new HashMap<String, String>();
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Map<String, String> getDocData() {
		return docData;
	}
	public void setDocData(Map<String, String> docData) {
		this.docData = docData;
	}

}
