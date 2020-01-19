package com.cc.owl.controller;

import com.cc.common.domain.Department;
import com.cc.common.domain.Employee;
import com.cc.common.domain.Student;
import com.cc.common.exception.UserNotExistException;
import com.cc.owl.mapdao.DepartmentDao;
import com.cc.owl.mapdao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OwlController {
    //SpringController默认为单例,此处dao对象后面移动到service
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    ApplicationContext applicationContext;

    @CrossOrigin(origins = "*")
    @RequestMapping("/hello2")
    @ResponseBody
    public Student hello(@RequestParam("user") String user){
        if ("aaa".equals(user)){
            throw new UserNotExistException();
        }
        Student zhangsan = new Student();
        zhangsan.setName("lisi");
        zhangsan.setAge(String.valueOf(new Date()));
        zhangsan.setGender("male");
        return zhangsan;
    }

    //页面请求
    @GetMapping("/index/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav=new ModelAndView("/socket1");
        mav.addObject("userId", userId);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Map pushToWeb(@PathVariable String cid, String message) {
        Map result = new HashMap();
        try {
            WebSocketServer.sendInfo(message, cid);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
            //登录成功,防止表单重复提交,可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            map.put("msg", "用户名密码错误!");
            return "index";
        }
    }

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门,在页面显示
        getDepts(model);
        return "emp/add";
    }

    private void getDepts(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
    }

    //员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加后来到员工列表页面
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //来到修改页面查出当前员工回显可改
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        getDepts(model);
        return "emp/add";
    }

//    //员工修改
//    @PutMapping("/emp")
//    public String updateEmp(){
////        System.out.println(employee.toString());
////        employeeDao.save(employee);
//        return "redirect:/emps";
//    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
            System.out.println("/////////////////"+name);
        }
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
            System.out.println("/////////////////"+name);
        }
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
