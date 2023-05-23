package com.bank.documentmanager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.documentmanager.data.CommentRepository;
import com.bank.documentmanager.data.PostRepository;
import com.bank.documentmanager.entity.Comment;
import com.bank.documentmanager.entity.Post;
import com.bank.documentmanager.exception.BadRequestException;
import com.bank.documentmanager.exception.RestExecutionException;
import com.bank.documentmanager.request.CommentRequest;
import com.bank.documentmanager.request.PostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(path="/posts")
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) throws JsonProcessingException, RestExecutionException, BadRequestException{
		Post createdPost = postRepository.create(new Post(postRequest.getDocumentId(), postRequest.getContent(), postRequest.getCreatedBy()));
		return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully with ID: " + createdPost.getId());
	}
	
	@GetMapping("/{documentId}")
	public ResponseEntity<List<Post>> getPostsByDocumentId(@PathVariable String documentId) throws JsonProcessingException, RestExecutionException{
		List<Post> posts = postRepository.retrievePostsForDocument(documentId);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<String> addCommentToPost(@RequestBody CommentRequest commentRequest) throws JsonProcessingException, RestExecutionException, BadRequestException{
		Comment createdComment = commentRepository.create(new Comment(commentRequest.getPostId(), commentRequest.getContent(), commentRequest.getCreatedBy()));
		return ResponseEntity.status(HttpStatus.CREATED).body("Comment added successfully with ID: " + createdComment.getId());
	}
	
	@GetMapping("/{postId}/comments")
	public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String postId) throws JsonProcessingException, RestExecutionException{
		List<Comment> comments = commentRepository.retrieveCommentsForPost(postId);
		return ResponseEntity.status(HttpStatus.OK).body(comments);
	}
	
}
