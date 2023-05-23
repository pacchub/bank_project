package com.bank.documentmanager.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bank.documentmanager.exception.BadRequestException;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@Component
public class Post implements Serializable {
	
	private String id;
	private String documentId;
	private String content;
	private String createdBy;
	private String createdTime;
	
	public Post(String documentId, String content, String createdBy) throws BadRequestException {
		this.id = UUID.randomUUID().toString();
		this.documentId = documentId;
		this.content = content;
		this.createdBy = createdBy;
		this.createdTime = LocalDateTime.now().toString();
	}

	public String getId() {
		return id;
	}

	public String getDocumentId() {
		return documentId;
	}

	public String getContent() {
		return content;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreatedTime() {
		return createdTime;
	}

}
