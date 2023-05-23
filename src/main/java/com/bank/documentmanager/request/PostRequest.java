package com.bank.documentmanager.request;

import java.io.Serializable;

import lombok.Builder;

@SuppressWarnings("serial")
@Builder
public class PostRequest implements Serializable {

	private String documentId;
	private String content;
	private String createdBy;
	
	public String getDocumentId() {
		return documentId;
	}
	public String getContent() {
		return content;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	
}
