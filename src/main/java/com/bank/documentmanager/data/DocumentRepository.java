package com.bank.documentmanager.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.documentmanager.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, String>{

	List<Document> findAll();
	
	Optional<Document> findByDocumentId(String documentId);
	
}
