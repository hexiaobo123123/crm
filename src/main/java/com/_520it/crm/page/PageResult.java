package com._520it.crm.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by heyuanbo on 2017/7/10.
 */
//我们的分页对象和我们的要传输我们的json数据格式
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private Integer total;//因为我们的数据是这种格式才能接受我们的数据
    private List rows;
}
