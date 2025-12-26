package org.example.firstproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1.DTO->entity 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        //2.db에 대상 타깃 유무 조회
        Article target= articleRepository.findById(id).orElse(null);
        //3.대상 엔티티가 없는 경우 잘못된 요청 처리
        if(target==null||id!=article.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        //4.대상 엔티티가 있는 경우 수정 내용으로 업데이트하고 정상 응답(200)보내기
        target.patch(article);
        Article updated= articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        //1.대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2.잘못된 요청 처리
        if(target==null){
            return null;
        }
        //3.대상 삭제하기
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1.dto묶음 엔티티묶음으로 변환
        List<Article> articleList=dtos.stream().map(dto->dto.toEntity()).collect(Collectors.toList());
        //2.엔티티묶음Db에저장
        articleList.stream().forEach(article->articleRepository.save(article));
        //3.강제예외발생시키기
        articleRepository.findById(-1L).orElseThrow(()->new IllegalArgumentException("결제 실패!"));
        //4.결과값반환하기
        return articleList;
    }
}
