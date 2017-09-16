package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@Controller
public class MainController {
    @RequestMapping("/main")
    public String show(){
        return "main";
    }
}
