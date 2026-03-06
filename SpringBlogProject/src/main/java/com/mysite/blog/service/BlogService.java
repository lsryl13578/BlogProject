package com.mysite.blog.service;

import org.springframework.stereotype.Service;

import com.mysite.blog.domain.Article;
import com.mysite.blog.dto.AddArticleRequest;
import com.mysite.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
	
	private final PostRepository postRepository;
	
	// 블로그 글 추가 메서드
	public Article save(AddArticleRequest request) {
		return postRepository.save(request.toEntity());
	}
}
