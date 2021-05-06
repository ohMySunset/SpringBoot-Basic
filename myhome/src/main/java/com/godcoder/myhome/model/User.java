package com.godcoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {  // 유저 데이터 빈즈

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean enabled;

    //@OneToOne -> 사용자 - 사용자 정보
    //@OneToMany -> 사용자 - 게시판
    //@ManyToOne -> 게시판 - 사용자
    //@ManyToMany -> 사용자 - 권한

    // 서로 연관된 두 테이블간의 조회방법 설정 : FetchType
    // EAGER
    //OneToOne
    //ManyToMany
    // LAZY
    //OneToMany
    //ManyToMany

    // 양방향 매핑
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private List<Role> roles = new ArrayList<>();

    //JPA <- Hibernate <- Spring Data JPA
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Board> boards = new ArrayList<>();
}
