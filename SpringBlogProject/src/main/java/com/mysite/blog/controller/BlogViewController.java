package com.mysite.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.blog.domain.Article;
import com.mysite.blog.dto.ArticleListViewResponse;
import com.mysite.blog.dto.ArticleViewResponse;
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
		
		return "articleList";		// articleList.html이라는 이름의 뷰 파일 조회
	}
	
	// 블로그 특정 글 화면을 반환하는 컨트롤러 메서드
	@GetMapping("/articles/{id}")
	public String getArticle(@PathVariable("id") Long id, Model model) {
		Article article = blogService.findById(id);
		model.addAttribute("article", new ArticleViewResponse(article));
		
		return "article";			// article.html이라는 이름의 뷰 파일 조회
	}
	
	// 새 블로그 글 작성 화면을 반환하는 컨트롤러 메서드
	@GetMapping("/new-article")
	// id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑 (id는 없을 수도 있음)
	public String newArticle(@RequestParam(value = "id", required = false) Long id, Model model) {
		if (id == null) {		// id가 없으면 생성
			model.addAttribute("article", new ArticleViewResponse());
		} else {		// id가 있으면 수정
			Article article = blogService.findById(id);
			model.addAttribute("article", new ArticleViewResponse(article));
		}
		
		return "newArticle";		// newArticle.html이라는 이름의 뷰 파일 조회
	}
}
