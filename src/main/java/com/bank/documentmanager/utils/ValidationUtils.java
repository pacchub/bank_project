package com.bank.documentmanager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bank.documentmanager.data.DocumentRepository;
import com.bank.documentmanager.data.PostRepository;
import com.bank.documentmanager.exception.BadRequestException;

@Service
public class ValidationUtils {
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	public static void isPdfFile(MultipartFile file) throws BadRequestException {
		if(!MediaType.APPLICATION_PDF_VALUE.equals(file.getContentType())) 
			throw new BadRequestException("Invalid document type - only PDF is accepted");
	}
	
}
