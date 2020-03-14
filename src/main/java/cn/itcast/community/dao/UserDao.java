package cn.itcast.community.dao;

import cn.itcast.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {
}
