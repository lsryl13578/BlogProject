package com.mysite.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.blog.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}