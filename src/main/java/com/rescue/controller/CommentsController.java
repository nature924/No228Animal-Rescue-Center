package com.rescue.controller;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Admin;
import com.rescue.entity.Comments;
import com.rescue.entity.User;
import com.rescue.service.CommentsService;
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
public class CommentsController {

    @Autowired
    CommentsService service;

    //删除评论
    @RequestMapping("/deleteComments")
    @ResponseBody
    public String deleteComments(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteComments(id);
        message = "yes";
        return message;
    }

    //新增评论
    @RequestMapping("/addComments")
    @ResponseBody
    public String addComments(HttpServletRequest request, Comments comments){
        String message = "no";
        User user = (User)request.getSession().getAttribute("user");
         if(user != null){
            comments.setUid(user.getId());
            comments.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            service.addComments(comments);
            message = "yes";
        }
        return message;
    }

    //修改评论
    @RequestMapping("/updateComments")
    @ResponseBody
    public String updateComments(Comments comments, Model model, HttpSession httpSession){
        String message = "no";
        service.updateComments(comments);
        message = "yes";
        return message;
    }


    //修改评论
    @RequestMapping("/getCommentsById")
    @ResponseBody
    public Comments getCommentsById(Integer id, Model model, HttpSession httpSession){
        return service.getCommentsById(id);
    }

    //查询评论
    @RequestMapping("/findCommentss")
    public String findallStudent(Model model, Comments comments,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (comments.getDetail() != null){
            request.setAttribute("detail",comments.getDetail());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Comments> pageInfo = service.findComments(comments,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findCommentss");
        return "comments/list";
    }
    
}
