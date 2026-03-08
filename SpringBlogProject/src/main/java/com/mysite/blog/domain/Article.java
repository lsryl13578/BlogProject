package com.mysite.blog.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)			// 엔티티의 생성 및 수정 시간 자동 감시와 기록을 위한 어노테이션
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
	
	@CreatedDate										// 엔티티가 생성될 때 생성 시간 저장
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")						// 엔티티가 수정될 때 수정 시간 저장
	private LocalDateTime updatedAt;
	
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