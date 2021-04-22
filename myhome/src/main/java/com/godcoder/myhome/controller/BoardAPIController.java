package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

// RestAPI + JPA + Thymeleaf

@RestController
@RequestMapping("/api")
public class BoardAPIController {

    @Autowired
    private BoardRepository br;

     // 리스트를 불러오는 메서드
     @GetMapping("/boards")
     List<Board> getList(@RequestParam(required = false, defaultValue = "") String title,
                         @RequestParam(required = false) String content){
         // 비어있을 경우 전체 조회
         if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
             return br.findAll();
         }else{
         // 데이터가 넘어올 경우 해당 데이터 조회
             return br.findByTitleOrContent(title, content);
         }
     }

     // 한 개의 정보를 저장하는 메서드
     @PostMapping("/boards")
     Board newBoard(@RequestBody Board b){
         return br.save(b);
     }

     // id 값으로 한 개의 정보를 조회하는 메서드
     // id 값이 있으면 반환 없으면 null 반환
     @GetMapping("/boards/{id}")
     Board one(@PathVariable Long id){
         return br.findById(id).orElse(null);
     }

     // 데이터를 수정하는 메서드
     @PutMapping("/boards/{id}")
     Board replaceBoard(@RequestBody Board b, @PathVariable Long id){
         // id와 일치하는 값을 찾아서 수정한 데이터를 저장
         return br.findById(id)
                 .map(board -> {
                     board.setTitle(b.getTitle());
                     board.setContent(b.getContent());
                     return br.save(board);
                 })
                 .orElseGet(() -> {
                     b.setId(id);
                     return br.save(b);
                 });
     }

     // 데이터를 삭제하는 메서드
     @DeleteMapping("/boards/{id}")
     void deleteBoard(@PathVariable Long id){
         br.deleteById(id);
     }
}
