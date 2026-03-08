package com.mysite.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.blog.dto.ArticleListViewResponse;
import com.mysite.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

	private final BlogService blogService;
	
	// 블로그 글 목록 화면을 반환하는 컨트롤러 메서드
	@GetMapping("/articles")
	public String getArticles(Model model) {
		List<ArticleListViewResponse> articles = blogService.findAll().stream()
				.map(ArticleListViewResponse::new)
				.toList();
		
		model.addAttribute("articles", articles);		// 블로그 글 리스트 저장
		
		return "articleList";		// articleList.html라는 이름의 뷰 파일 조회
	}
}
