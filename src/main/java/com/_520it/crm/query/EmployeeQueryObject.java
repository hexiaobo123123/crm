package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@Setter
@Getter
@ToString
public class EmployeeQueryObject extends QueryObject{
    private String keyword;
    public String getKeyword(){
        if(keyword!=null&&"".equals(keyword.trim())){
                return null;
    }
    return keyword;
    }
}
