package com.godcoder.myhome.validator;

import com.godcoder.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

// 자바코드를 활용한 유효성 검사
@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors){
        Board b = (Board) obj;
        // 공백이 있는지 검사
        if(StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content", "key","내용을 입력하세요.");
        }
    }
}
