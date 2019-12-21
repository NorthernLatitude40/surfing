package com.cc.owl.controller;

import com.cc.domain.Department;
import com.cc.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Wayne
 * @Date: 2019/12/19 17:09
 * @Version: 1.0
 */
@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id, HttpServletRequest req, HttpServletResponse res){
        System.out.println("req"+req.getCharacterEncoding());
        System.out.println("res"+res.getCharacterEncoding());
        res.setCharacterEncoding("utf-8");
        System.out.println("res"+res.getCharacterEncoding());
//        try {
//            PrintWriter writer = res.getWriter();
//            System.out.println(writer.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Department deptById = departmentMapper.getDeptById(id);
        System.out.println(deptById);
        return deptById;
    }

    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }

    @GetMapping("/dept1")
    public String insertDept1(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        System.out.println("req"+req.getCharacterEncoding());
//        System.out.println("res"+res.getCharacterEncoding());
        //设置服务器端的编码，默认是ISO-8859-1；
        // 该方法必须在response.getWriter()之前进行设置
//        res.setCharacterEncoding("GBK");
        //通知浏览器服务器发送的数据格式是text/html，
        // 并要求浏览器使用utf-8进行解码。服务器客户端编码一致,且是中文
        res.setHeader("Content-Type", "text/html; charset=GBK");
//        System.out.println("res"+res.getCharacterEncoding());
//        res.getWriter().write("你好啊");
        return "你好啊";
    }
}
