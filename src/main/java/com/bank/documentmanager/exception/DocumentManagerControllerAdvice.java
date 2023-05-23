package com.bank.documentmanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.documentmanager.entity.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class DocumentManagerControllerAdvice {
	
	Logger logger = LoggerFactory.getLogger(DocumentManagerControllerAdvice.class);
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleBadRequestException(final BadRequestException ex) {
		logger.error(ex.getMessage());
		return new ErrorResponse(ex.getMessage());
	}
	
	@ExceptionHandler(RestExecutionException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleRestExecutionException(final RestExecutionException ex) {
		logger.error(ex.getMessage());
		return new ErrorResponse(ex.getMessage());
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleJsonProcessingException(final JsonProcessingException ex) {
		logger.error("Data source returned an invalid response", ex);
		return new ErrorResponse("Data source returned an invalid response: " + ex.getMessage());
	}

}
