package org.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.firstproject.dto.CommentDto;

@Entity//해당클래스가엔티티임을선언하고클래스필드를바탕으로DB에테이블생성
@Getter//각 필드 값을 조회할수있는getter메서드 자동생성
@ToString//모든필드를출력할수있는toString 메서드자동생성
@AllArgsConstructor//모든필드를매개변수로갖는생성자자동생성
@NoArgsConstructor//매개변수가아예없는기본생성자자동생성
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//DB가자동으로1씩증가
    private Long id;//대표키

    @ManyToOne//Comment엔티티와Article엔티티 다대일관계로설정
    @JoinColumn(name="article_id")
    private Article article;//부모게시글

    @Column
    private String nickname;//댓글단사람

    @Column
    private String body;//댓글본문

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if(dto.getId()!=null)
            throw new IllegalArgumentException("댓글생성실패!댓글의id가없어야합니다");
        if(dto.getArticleId()!=article.getId())
            throw new IllegalArgumentException("댓글생성실패!게시글의id가잘못됐습니다");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외발생
        if(this.id!=dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됨");
        //객체 갱신
        if(dto.getNickname()!=null)//수정할닉네임데이터가있다면
            this.nickname=dto.getNickname();//내용 반영
        if(dto.getBody()!=null)
            this.body=dto.getBody();
    }
}
