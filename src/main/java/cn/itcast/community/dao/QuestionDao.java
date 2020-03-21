package cn.itcast.community.dao;

import cn.itcast.community.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionDao extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {
}
