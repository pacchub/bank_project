package com.bank.documentmanager.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bank.documentmanager.entity.Comment;
import com.bank.documentmanager.exception.RestExecutionException;
import com.bank.documentmanager.utils.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CommentRepository {
	
	Logger logger = LoggerFactory.getLogger(CommentRepository.class);
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	RestUtils restUtils;
	
	@Value("${posts.api.base-url}")
	private String baseUrl;
	
	public Comment create(Comment comment) throws RestExecutionException, JsonProcessingException {
		logger.info("Creating new comment with ID: {} for post with ID: {}", comment.getId(), comment.getPostId());
		String responseBody = restUtils.sendPostRequest(baseUrl + "comments", comment);
		logger.info("New comment created: {}", responseBody);
		return mapper.readValue(responseBody, Comment.class);
	}
	
	public List<Comment> retrieveCommentsForPost(String postId) throws RestExecutionException, JsonProcessingException {
		logger.info("Retrieving comments for post with ID: {}", postId);
		String responseBody = restUtils.sendGetRequest(baseUrl + "comments?postId=" + postId);
		logger.info("Retrieved comments for post with ID {} : {}", postId, responseBody);
		return mapper.readValue(responseBody, new TypeReference<List<Comment>>(){});
	}

}
