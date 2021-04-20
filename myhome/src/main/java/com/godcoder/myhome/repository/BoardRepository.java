package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// JPARepository를 상속한 인터페이스
public interface BoardRepository extends JpaRepository<Board,Long> {

      // Query Creation -> Spring Data JPA 참고하기
      // https://docs.spring.io/spring-data/jpa/docs/2.5.0/reference/html/#reference
      List<Board> findByTitle(String title);
      List<Board> findByTitleOrContent(String title, String content);
}
