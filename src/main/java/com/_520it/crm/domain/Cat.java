package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by heyuanbo on 2017/7/11.
 */
@Setter
@Getter
@ObjectProp("小猫管理")
public class Cat {
    @ObjectProp("编号")
    private Long id;
    @ObjectProp("名字")
    private String name;
    @ObjectProp("年龄")
    private String age;
}
