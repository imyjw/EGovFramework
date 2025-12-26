package org.example.firstproject.repository;

import org.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의모든댓글조회 - 네이티브 쿼리 메서드 만드는법 1. @Query로 쿼리 메서드 구현
    @Query(value="SELECT * FROM comment WHERE article_id = :articleId", nativeQuery=true)
    List<Comment> findByArticleId(Long articleId);

    //특정 닉네임의 모든댓글조회 - 2.orm.xml파일 이용하기
    List<Comment> findByNickname(String nickname);

}
