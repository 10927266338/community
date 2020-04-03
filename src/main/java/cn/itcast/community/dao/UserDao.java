package cn.itcast.community.dao;

import cn.itcast.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

    User findByToken(String token);
    User findById(Integer id);
    User findByAccountId(String accountId);
}
