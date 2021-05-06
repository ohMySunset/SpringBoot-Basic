package com.godcoder.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role { // 유저의 권한 데이터 빈즈

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 양방향 매핑
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> roles;

}
