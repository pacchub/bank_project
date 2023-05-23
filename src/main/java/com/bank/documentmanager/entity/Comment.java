package com.bank.documentmanager.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bank.documentmanager.exception.BadRequestException;
import com.bank.documentmanager.exception.RestExecutionException;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@Component
public class Comment implements Serializable {
	
	private String id;
	private String postId;
	private String content;
	private String createdBy;
	private String createdTime;
	
	public Comment(String postId, String content, String createdBy) throws BadRequestException, RestExecutionException, JsonProcessingException {
		this.id = UUID.randomUUID().toString();
		this.postId = postId;
		this.content = content;
		this.createdBy = createdBy;
		this.createdTime = LocalDateTime.now().toString();
	}

	public String getId() {
		return id;
	}

	public String getPostId() {
		return postId;
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
