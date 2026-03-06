package com.mysite.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.blog.domain.Article;
import com.mysite.blog.dto.AddArticleRequest;
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
}
