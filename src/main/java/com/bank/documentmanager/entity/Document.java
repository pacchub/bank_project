package com.bank.documentmanager.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import com.bank.documentmanager.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "documents")
public class Document implements Serializable {
	
	public Document() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String documentId;
	
	@Column
	private String fileName;
	
	@Lob
	@Column(name = "fileData")
	@JsonIgnore
	private byte[] fileData;
	
	@Column
	private LocalDateTime createdTime;
	
	@Column
	private String uploadedBy;
	
	public Document(String fileName, byte[] fileData) {
		this.fileName = fileName;
		this.fileData = fileData;
		this.createdTime = LocalDateTime.now();
		this.uploadedBy = Constants.USER;
	}
	
	public String getDocumentId() {
		return documentId;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}
	
}
