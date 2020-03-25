package cn.itcast.community.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;   //问题标题
    @Column(columnDefinition = "varchar(1000) default 'LTD'")
    private String description;     //问题内容
    private String tag;         //标签
    private Long gmtCreate;     //发布时间
    private Long gmtModified;
    private Integer creator;      //用户id
    private Integer viewCount=0;      //回复数量
    private Integer commentCount=0;   //浏览数量
    private Integer likeCount=0;      //点赞数量
}
