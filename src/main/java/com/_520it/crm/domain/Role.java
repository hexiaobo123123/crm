package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyuanbo on 2017/7/11.
 */
@Setter
@Getter
@ObjectProp("角色管理")
public class Role {
    @ObjectProp("id")
    private Long id;
    @ObjectProp("编号")
    private String sn;
    @ObjectProp("名称")
    private String name;
    @ObjectProp("权限集合 ")
    private List<Permission> perms = new ArrayList<>();

}
