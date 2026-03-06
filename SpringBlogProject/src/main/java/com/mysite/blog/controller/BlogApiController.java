package com.mysite.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.blog.domain.Article;
import com.mysite.blog.dto.AddArticleRequest;
import com.mysite.blog.dto.ArticleResponse;
import com.mysite.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@RestController						// Http의 Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequiredArgsConstructor
public class BlogApiController {
	
	private final BlogService blogService;
	
	// HTTP 메서드가 POST일 때 전달받은 URL과 동일 시 메서드로 매핑
	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
		Article savedArticle = blogService.save(request);
		
		// 요청힌 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}
	
	// 전체 게시글 목록을 조회하는 GET API 요청을 처리
	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles() {
		
		// 서비스 계층에서 전체 Article 엔티티 목록을 조회
		List<ArticleResponse> articles = blogService.findAll()
				// 조회된 Article 엔티티들을 ArticleResponse DTO로 변환
				.stream()
				.map(ArticleResponse::new)
				
				// 변환된 결과를 List로 수집
				.toList();
		
		// HTTP 상태 코드 200(OK)와 함께 게시글 목록을 JSON 형태로 응답
		return ResponseEntity.ok()
				.body(articles);
	}
}
