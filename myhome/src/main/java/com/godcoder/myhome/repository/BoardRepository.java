package com.godcoder.myhome.repository;

import com.godcoder.myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// JPARepository를 상속한 인터페이스
public interface BoardRepository extends JpaRepository<Board,Long> {

      // Query Creation -> Spring Data JPA 참고하기
      // https://docs.spring.io/spring-data/jpa/docs/2.5.0/reference/html/#reference

      List<Board> findByTitle(String title);
      // 제목 혹은 내용을 기준으로 데이터 조회
      List<Board> findByTitleOrContent(String title, String content);

      // 제목 혹은 내용에 검색어가 포함되는 경우 일치하는 값 조회
      Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
