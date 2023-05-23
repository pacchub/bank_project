package com.bank.documentmanager.request;

import java.io.Serializable;

import lombok.Builder;

@SuppressWarnings("serial")
@Builder
public class CommentRequest implements Serializable{

	private String postId;
	private String content;
	private String createdBy;
	
	public String getPostId() {
		return postId;
	}
	public String getContent() {
		return content;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	
}
