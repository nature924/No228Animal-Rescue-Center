package com.rescue.controller;

import com.rescue.entity.User;
import com.rescue.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService service;

    //删除用户
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(Integer id,Model model,HttpSession httpSession){
        String message = "no";
        service.deleteUser(id);
        message = "yes";
        return message;
    }

    //新增用户
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user,Model model,HttpSession httpSession){
        String message = "no";
        User users = service.selectUserByPhone(user.getPhone());
        if(users == null){
            user.setRegiste_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            service.addUser(user);
            message = "yes";
        }
        return message;
    }

    //修改用户
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(User user, Model model, HttpSession httpSession){
        String message = "no";
        service.updateUser(user);
        message = "yes";
        return message;
    }

    //修改用户密码
    @RequestMapping("/updateUserPass")
    @ResponseBody
    public String updateUserPass(User user, Model model, HttpSession httpSession){
        String message = "no";
        service.updateUserPass(user);
        message = "yes";
        return message;
    }
    //修改用户头像
    @RequestMapping("/updateUserTx")
    @ResponseBody
    public String updateUserTx(User user, Model model, HttpSession httpSession){
        String message = "no";
        service.updateUserTx(user);
        message = "yes";
        return message;
    }

    //修改用户
    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Integer id, Model model, HttpSession httpSession){
        return service.getUserById(id);
    }

    //查询用户
    @RequestMapping("/findUsers")
    public String findallStudent(Model model, User user,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (user.getRealname() != null){
            request.setAttribute("key",user.getRealname());
        }
        User users = (User) httpSession.getAttribute("user");
        user.setId(users != null ? users.getId() : null);
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<User> pageInfo = service.findUser(user,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findUsers");
        return "user/userHome";
    }



}
