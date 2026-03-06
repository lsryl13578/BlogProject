package com.mysite.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.blog.domain.Article;
import com.mysite.blog.dto.AddArticleRequest;
import com.mysite.blog.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
	
	private final ArticleRepository articleRepository;
	
	// 블로그 글 추가 메서드
	public Article save(AddArticleRequest request) {
		return articleRepository.save(request.toEntity());
	}
	
	// article 테이블에 저장되어 있는 모든 데이터 조회
	public List<Article> findAll() {
		return articleRepository.findAll();
	}
}
