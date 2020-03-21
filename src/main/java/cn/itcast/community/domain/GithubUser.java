package cn.itcast.community.domain;

import lombok.Data;

import javax.persistence.*;

@Data
public class GithubUser {


    private Long id;
    private String name;
    private String bio;
    private String avatar_url;

}
