package com.rescue.controller;

import com.rescue.entity.Admin;
import com.rescue.entity.Board;
import com.rescue.entity.User;
import com.rescue.service.BoardService;
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
public class BoardController {

    @Autowired
    BoardService service;

    //删除帖子
    @RequestMapping("/deleteBoard")
    @ResponseBody
    public String deleteBoard(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteBoard(id);
        message = "yes";
        return message;
    }

    //新增帖子
    @RequestMapping("/addBoard")
    @ResponseBody
    public String addBoard(HttpServletRequest request, Board board){
        String message = "no";
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        User user = (User)request.getSession().getAttribute("user");
        if(admin != null){
            board.setTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            service.addBoard(board);
            message = "yes";
        }else if(user != null){
            board.setEditor(user.getRealname());
            board.setUid(user.getId());
            board.setTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            service.addBoard(board);
            message = "yes";
        }

        return message;
    }

    //修改帖子
    @RequestMapping("/updateBoard")
    @ResponseBody
    public String updateBoard(Board board, Model model, HttpSession httpSession){
        String message = "no";
        service.updateBoard(board);
        message = "yes";
        return message;
    }


    //修改帖子
    @RequestMapping("/getBoardById")
    @ResponseBody
    public Board getBoardById(Integer id, Model model, HttpSession httpSession){
        return service.getBoardById(id);
    }

    //查询帖子
    @RequestMapping("/findBoards")
    public String findallStudent(Model model, Board board,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (board.getTitle() != null){
            request.setAttribute("names",board.getTitle());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Board> pageInfo = service.findBoard(board,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findBoards");
        return "board/list";
    }
    
}
