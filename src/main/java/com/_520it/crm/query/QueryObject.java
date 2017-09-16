package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by heyuanbo on 2017/7/10.
 */
//我们的分页对象
    @Setter
    @Getter
public class QueryObject {
    private Integer page=1;
    private Integer rows=10;
    //我们的开始的索引
    public Integer getStart(){
        return (this.page-1)*rows;
    }
}
