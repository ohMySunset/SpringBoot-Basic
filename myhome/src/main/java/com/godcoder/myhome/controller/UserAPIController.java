package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.User;
import com.godcoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPIController {

    @Autowired
    private UserRepository br;

     // 리스트를 불러오는 메서드
     @GetMapping("/users")
     List<User> all(){
             return br.findAll();
     }

     // 한 개의 정보를 저장하는 메서드
     @PostMapping("/users")
     User newUser(@RequestBody User b){
         return br.save(b);
     }

     // id 값으로 한 개의 정보를 조회하는 메서드
     // id 값이 있으면 반환 없으면 null 반환
     @GetMapping("/users/{id}")
     User one(@PathVariable Long id){
         return br.findById(id).orElse(null);
     }

     // 데이터를 수정하는 메서드
     @PutMapping("/users/{id}")
     User replaceUser(@RequestBody User b, @PathVariable Long id){
         // 파라미터로 받은 id와 일치하는 값을 찾아서 수정한 데이터를 저장
         return br.findById(id)
                 .map(user -> {
//                     user.setTitle(b.getTitle());
//                     user.setContent(b.getContent());

                     return br.save(user);
                 })
                 .orElseGet(() -> {
                     b.setId(id);
                     return br.save(b);
                 });
     }

     // 데이터를 삭제하는 메서드
     @DeleteMapping("/users/{id}")
     void deleteUser(@PathVariable Long id){
         br.deleteById(id);
     }
}
