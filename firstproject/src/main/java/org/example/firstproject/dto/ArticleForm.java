package org.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.firstproject.entity.Article;

@AllArgsConstructor
@ToString

public class ArticleForm {
    private Long id;
    private String title;
    private String content;


    /* @AllArgsConstructor로 리팩터링
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    */

    /* @ToString으로 리팩터링
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */

    public Article toEntity() {
        return new Article(id,title,content);
    }
}
