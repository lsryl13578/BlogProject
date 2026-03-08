package com.mysite.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.blog.domain.Article;
import com.mysite.blog.dto.AddArticleRequest;
import com.mysite.blog.dto.UpdateArticleRequest;
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
	
	// 블로그 특정 글 조회 메서드
	public Article findById(long id) {
		return articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found: " + id));
	}
	
	// 블로그 글 삭제 메서드
	public void delete(long id) {
		articleRepository.deleteById(id);
	}
	
	// 블로그 글 수정 메서드
	@Transactional		// 데이터 수정 과정에서 예외 발생 시 롤백을 위해 트랜잭션 적용
	public Article update(long id, UpdateArticleRequest request) {
		Article article = articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found: " + id));
		
		article.update(request.getTitle(), request.getContent());
		
		return article;
	}
}
