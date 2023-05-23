package com.bank.documentmanager.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public class ErrorResponse implements Serializable{
	
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
