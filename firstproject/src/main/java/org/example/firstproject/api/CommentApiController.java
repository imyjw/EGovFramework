package org.example.firstproject.api;


import org.example.firstproject.dto.CommentDto;
import org.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1.댓글조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        //서비스에 위임
        List<CommentDto> dtos=commentService.comments(articleId);//조회할댓글의 부모게시글id
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2.댓글생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto);//생성할 댓글의 부모게시글id와 생성 데이터인 dto
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //3.댓글수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto updateDto=commentService.update(id,dto);//수정할 댓글의 id와 수정데이터인 dto
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }
    //4.댓글삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        //서비스에위임
        CommentDto deletedDto=commentService.delete(id);
        //결과응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
