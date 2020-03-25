package cn.itcast.community.service;

import cn.itcast.community.dao.QuestionDao;
import cn.itcast.community.dao.UserDao;
import cn.itcast.community.dto.QuestionDTO;
import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    public List<QuestionDTO> findAll() {
        List<Question> questions = questionDao.findAll();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:questions){
            User user = userDao.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
