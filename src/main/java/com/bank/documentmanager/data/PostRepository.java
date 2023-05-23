package com.bank.documentmanager.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bank.documentmanager.entity.Post;
import com.bank.documentmanager.exception.RestExecutionException;
import com.bank.documentmanager.utils.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class PostRepository {
	
	Logger logger = LoggerFactory.getLogger(PostRepository.class);
	
	public PostRepository(ObjectMapper mapper, RestUtils restUtils) {
		this.mapper = mapper;
		this.restUtils = restUtils;
	}
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	RestUtils restUtils;
	
	@Value("${posts.api.base-url}")
	private String baseUrl;
	
	public Post create(Post post) throws RestExecutionException, JsonProcessingException {
		logger.info("Creating new post with ID: {} for document with ID: {}", post.getId(), post.getDocumentId());
		String responseBody = restUtils.sendPostRequest(baseUrl + "posts", post);
		logger.info("New post created: {}", responseBody);
		return mapper.readValue(responseBody, Post.class);
	}
	
	public List<Post> retrievePostsForDocument(String documentId) throws RestExecutionException, JsonProcessingException {
		logger.info("Retrieving posts for document with ID: {}", documentId);
		String responseBody = restUtils.sendGetRequest(baseUrl + "posts?documentId=" + documentId);
		logger.info("Retrieved posts for document with ID {} : {}", documentId, responseBody);
		return mapper.readValue(responseBody, new TypeReference<List<Post>>(){});
	}
	
	public Post retrievePostById(String postId) throws RestExecutionException, JsonProcessingException {
		String responseBody = restUtils.sendGetRequest(baseUrl + "posts?id=" + postId);
		List<Post>posts = mapper.readValue(responseBody, new TypeReference<List<Post>>(){});
		if(posts.isEmpty())
			return null;
		else
			return posts.get(0);
	}

}
