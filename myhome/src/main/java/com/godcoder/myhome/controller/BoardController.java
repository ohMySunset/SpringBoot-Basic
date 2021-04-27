package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
        // JPA 페이징 처리 (page 기본값 0)
        //Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20));
        //Page<Board> boards = boardRepository.findAll(pageable);

        // 검색 + 페이징 처리
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4); // boards.getPageable().getPageNumber() -> 현재 페이지 넘버
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){

        if(id==null){
            // id값이 null일 때
            model.addAttribute("board", new Board());
        }else{
            // id값에 일치하는 데이터 불러오기
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult br){

        // 유효성 검사 (자바 코드로)
        boardValidator.validate(board, br);

        // 유효성 검사 (어노테이션 활용)
        if(br.hasErrors()){
            return "board/form";
        }
        // 게시물 저장 (이미 존재하는 경우 수정)
        boardRepository.save(board);
        //board 데이터를 저장만하기 때문에 다시 조회된 값을 뿌려주기 위해서 redirect를 써준다.
        return "redirect:/board/list";
    }



}
