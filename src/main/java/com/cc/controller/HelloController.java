package com.cc.controller;

import com.cc.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ResponseBody
@Controller
public class HelloController {

    @CrossOrigin(origins = "*")
    @RequestMapping("/hello")
    public Student hello(){
        Student zhangsan = new Student();
        zhangsan.setName("zhangsan");
        zhangsan.setAge(String.valueOf(new Date()));
        zhangsan.setGender("male");
        return zhangsan;
    }
}
