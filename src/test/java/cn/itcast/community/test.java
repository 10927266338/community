package cn.itcast.community;


import cn.itcast.community.dao.QuestionDao;
import cn.itcast.community.dao.UserDao;

import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import org.hibernate.criterion.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;


import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommunityApplication.class)
public class test {
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    @Test
    public void run(){
        List<User> all = userDao.findAll();
    }
    @Test
    public void  run1(){
        Pageable pageable = PageRequest.of(0, 5);
        Page<Question> page = questionDao.findAll(pageable);
        for (Question question:page){
            System.out.println(question);
        }
        System.out.println("1");
        System.out.println(page);
        System.out.println("2");
        questionDao.findAll();
    }
    @Test
    public void run2(){
        Integer count = questionDao.findCount(1);
        System.out.println(count);
    }
    @Test
    public void run3(){
        Question question=new Question();
        question.setCreator(1);
        Pageable p = PageRequest.of(0, 2);
        User user=new User();
        user.setId(6);
        Page<Question> creator = questionDao.findAll(new Specification<Question>() {

            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> creator = root.get("creator");
                Predicate equal = criteriaBuilder.equal(creator, user.getId());
                return equal;
            }
        }, p);
        List<Question> content = creator.getContent();
        System.out.println(content);
    }
}
