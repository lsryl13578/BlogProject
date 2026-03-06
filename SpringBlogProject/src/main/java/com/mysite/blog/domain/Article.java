package com.mysite.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
	
	@Id	// id 필드를 기본키로 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 기본키 자동 1씩 증가
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;								// 게시글 제목 필드
	
	@Column(name = "content", nullable = false)
	private String content;								// 게시글 내용 필드
	
	@Builder											// 빌더 패턴을 통해 객체 생성
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	// 게시글 제목과 내용을 수정
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}