package cn.itcast.community.service;

import cn.itcast.community.dao.UserDao;
import cn.itcast.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        User dbUser = userDao.findByAccountId(user.getAccountId());
        if (dbUser==null){
            //创建
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            userDao.save(user);
        }else {
            //跟新
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(user.getGmtModified());
            dbUser.setToken(user.getToken());
            userDao.save(dbUser);
        }
    }
}
