package com.rescue.controller;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Admin;
import com.rescue.entity.Notify;
import com.rescue.entity.User;
import com.rescue.service.NotifyService;
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
public class NotifyController {

    @Autowired
    NotifyService service;

    //删除公告
    @RequestMapping("/deleteNotify")
    @ResponseBody
    public String deleteNotify(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteNotify(id);
        message = "yes";
        return message;
    }

    //新增公告
    @RequestMapping("/addNotify")
    @ResponseBody
    public String addNotify(HttpServletRequest request, Notify notify){
        String message = "no";
        notify.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        service.addNotify(notify);
        message = "yes";
        return message;
    }

    //修改公告
    @RequestMapping("/updateNotify")
    @ResponseBody
    public String updateNotify(Notify notify, Model model, HttpSession httpSession){
        String message = "no";
        service.updateNotify(notify);
        message = "yes";
        return message;
    }


    //修改公告
    @RequestMapping("/getNotifyById")
    @ResponseBody
    public Notify getNotifyById(Integer id, Model model, HttpSession httpSession){
        return service.getNotifyById(id);
    }

    //查询公告
    @RequestMapping("/findNotifys")
    public String findallStudent(Model model, Notify notify,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (notify.getTitle() != null){
            request.setAttribute("names",notify.getTitle());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Notify> pageInfo = service.findNotify(notify,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findNotifys");
        return "notify/list";
    }
    
}
