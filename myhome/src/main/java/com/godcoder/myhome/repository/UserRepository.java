package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // username : UQ제한조건 -> 1 또는 null 반환
    User findByUsername(String username);

}
