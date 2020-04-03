package cn.itcast.community.service;



import cn.itcast.community.dao.QuestionDao;
import cn.itcast.community.dao.UserDao;
import cn.itcast.community.dto.PaginationDTO;
import cn.itcast.community.dto.QuestionDTO;
import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    //分页查询
    public PaginationDTO findAll(Integer page, Integer size){
        //根据开始和每页显示多少查询
        Pageable pageable=PageRequest.of(page,size);
        Page<Question> questions =  questionDao.findAll(pageable);
        //创建了questionDTO集合
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        //创建了一个paginationDTO对象，封装了questionDTO及页数
        PaginationDTO paginationDTO=new PaginationDTO();
        //根据id获取用户
        for (Question question:questions){
            User user = userDao.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //将question复制到questionDTO
            BeanUtils.copyProperties(question,questionDTO);
            //给用户赋值
            questionDTO.setUser(user);
            //将questionDTO添加到集合了
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        //获取问题总数
        Integer count = questionDao.findCount();
        paginationDTO.setPaginnation(count,page,size);
        return paginationDTO;
    }




    public PaginationDTO findAll(Integer userId,Integer page, Integer size){
        //根据开始和每页显示多少查询
        Pageable pageable=PageRequest.of(page,size);
        Page<Question> questions =  questionDao.findAll(new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> creator = root.get("creator");
                Predicate predicate = criteriaBuilder.equal(creator, userId);
                return predicate;
            }
        }, pageable);
        //创建了questionDTO集合
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        //创建了一个paginationDTO对象，封装了questionDTO及页数
        PaginationDTO paginationDTO=new PaginationDTO();
        //根据id获取用户
        for (Question question:questions){
            User user = userDao.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //将question复制到questionDTO
            BeanUtils.copyProperties(question,questionDTO);
            //给用户赋值
            questionDTO.setUser(user);
            //将questionDTO添加到集合了
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        //获取问题总数
        Integer count = questionDao.findCount(userId);
        paginationDTO.setPaginnation(count,page,size);
        return paginationDTO;
    }


    public QuestionDTO findAll(Integer id) {
        Question question = questionDao.findQuestion(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userDao.findById(question.getCreator());
        questionDTO.setUser(user);
        return  questionDTO;
    }
}
