package com.crabar.service.demo;

import com.crabar.common.model.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wmh
 * Date: 14-7-30
 * Time: ионГ1:45
 */
@Service("answerService")
public class AnswerServiceImpl {

    public List<Answer> findAll() {
        return Answer.dao.find("select * from answer");
    }

}
