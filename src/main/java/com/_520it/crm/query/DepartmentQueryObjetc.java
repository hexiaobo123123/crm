package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by heyuanbo on 2017/7/11.
 */
@Setter
@Getter
public class DepartmentQueryObjetc extends QueryObject {
    private String keyword;
    public String getKeyword(){
        if(keyword!=null&&"".equals(keyword.trim())){
            return null;
        }
        return keyword;
    }
    }
