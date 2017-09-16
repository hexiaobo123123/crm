package com._520it.crm.page;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by heyuanbo on 2017/7/10.
 */
//专门做我们页面数据的提示
    @Setter
    @Getter
    @AllArgsConstructor
public class AjaxResult {
    //一个是我们的状态
    private Boolean success;
    private String msg;
    //使用一个参数的构造器如果失败是我们就可以闯入一个参数
    public AjaxResult(String msg) {
        this.msg = msg;
    }
}
