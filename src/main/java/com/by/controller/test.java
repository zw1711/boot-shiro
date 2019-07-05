package com.by.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${zw} on 2019/7/4.
 */
@RequestMapping("test")
public class test {

    @RequestMapping("list")
    public String toLoginPage(){

        return"listt";
    }

}
