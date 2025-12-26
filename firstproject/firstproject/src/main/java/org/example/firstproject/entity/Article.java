package org.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Article {
    @Id //엔티티대푯값지정
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //대푯값자동생성기능
    private Long id;
    @Column//title필드 선언. DB 테이블의 title 열과 연결됨
    private String title;
    @Column//content필드 선언. DB 테이블의 content 열과 연결됨
    private String content;

    public void patch(Article article) {
        if(article.title!=null)
            this.title = article.title;
        if(article.content!=null)
            this.content = article.content;
    }


/*
    public String getId() {     //@Getter로 대체
        return id;
    }
*/

    /* @AllArgsConstructor ,@ToString로 리팩터링
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

     */
}
