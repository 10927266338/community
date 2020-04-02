package cn.itcast.community.dao;

import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {
    @Query(value = "select count(1) from question",nativeQuery = true)
    public Integer findCount();
    @Query(value = "select count(1) from question where creator=?",nativeQuery = true)
    public Integer findCount(Integer userId);
}
