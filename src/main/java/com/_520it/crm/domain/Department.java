package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Department {
    //定义我们的常量来定义我们的状态
    public static final int EXIST = 0;//表示存在
    public static final int LEAVE = -1;//表示离开
    private Long id;

    private String sn;

    private String name;

    private int state;

}