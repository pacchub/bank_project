package com.bank.documentmanager.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.documentmanager.data.DocumentRepository;
import com.bank.documentmanager.entity.Document;


@RestController
@RequestMapping(path="/documents")
public class DocumentController {
	
	@Autowired
	DocumentRepository documentRepository;
	
	@PostMapping
	public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Document document = new Document(fileName, file.getBytes());
		Document savedDocument = documentRepository.save(document);
		return ResponseEntity.status(HttpStatus.CREATED).body("Document uploaded successfully with ID: " + savedDocument.getDocumentId());
	}
	
	@GetMapping
	public ResponseEntity<List<Document>> fetchAllDocuments() {
		List<Document> allDocuments = documentRepository.findAll(); 
		return ResponseEntity.status(HttpStatus.OK).body(allDocuments);
	}
	
	@GetMapping("/{documentId}")
	public ResponseEntity<Document> fetchDocumentById(@PathVariable String documentId) {
		Document documentById = documentRepository.findById(documentId).get();
		return ResponseEntity.status(HttpStatus.OK).body(documentById);
	}
	
	@GetMapping("/{documentId}/download")
	public ResponseEntity<byte[]> downloadDocumentById(@PathVariable String documentId) {
		Document documentById = documentRepository.findById(documentId).get();
		return ResponseEntity.status(HttpStatus.OK)
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentById.getFileName() + "\"")
	        .body(documentById.getFileData());
	}

}
