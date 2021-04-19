package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String list(Model model){
       List<Board> boards = boardRepository.findAll();
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

        //유효성 검사 (자바코드)
        boardValidator.validate(board, br);

        //유효성 검사 (어노테이션 활용)
        if(br.hasErrors()){
            return "board/form";
        }
        // 게시물 저장 (이미 존재하는 경우 수정)
        boardRepository.save(board);
        //board 데이터를 저장만하기 때문에 다시 조회된 값을 뿌려주기 위해서 redirect를 써준다.
        return "redirect:/board/list";
    }



}
