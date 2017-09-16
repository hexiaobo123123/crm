package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class Employee {
    //定义我们的常量来定义我们的状态
    public static final int EXIST = 0;//表示存在
    public static final int LEAVE = -1;//表示离开
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date inputtime;

    private int state;

    private Boolean admin;
    //我们的角色对象
    private List<Role> roles = new ArrayList<>();


}