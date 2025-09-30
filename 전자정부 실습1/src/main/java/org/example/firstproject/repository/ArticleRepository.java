package org.example.firstproject.repository;

import org.example.firstproject.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll(); // Iterable → ArrayList 수정
}
