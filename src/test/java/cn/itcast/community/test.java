package cn.itcast.community;


import cn.itcast.community.dao.UserDao;

import cn.itcast.community.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommunityApplication.class)
public class test {
    @Autowired
    private UserDao userDao;
    @Test
    public void run(){
        List<User> all = userDao.findAll();

    }
}
