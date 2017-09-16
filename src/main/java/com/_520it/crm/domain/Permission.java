package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by heyuanbo on 2017/7/11.
 */
@Setter
@Getter
@ObjectProp("权限管理")
public class Permission {
    @ObjectProp("id")
    private Long id;
    @ObjectProp("编码")
    private String sn;
    @ObjectProp("权限表达式")
    private String resource;
}
