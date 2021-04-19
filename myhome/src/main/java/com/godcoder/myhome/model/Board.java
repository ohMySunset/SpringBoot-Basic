package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board { // 게시글 데이터를 담을 빈즈객체

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //validation 어노테이션을 활용한 유효성 검사 (간편)
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자 이상 30자 이하입니다.")
    private String title;
    private String content;
}
