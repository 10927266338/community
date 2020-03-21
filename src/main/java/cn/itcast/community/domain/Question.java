package cn.itcast.community.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;   //问题标题
    private String description;     //问题补充
    private String tag;         //标签
    private Long gmtCreate;     //发布时间
    private Long gmtModified;
    private Integer creator;      //用户id
    private Integer viewCount;      //回复数量
    private Integer commentCount;   //浏览数量
    private Integer likeCount;      //点赞数量
}
