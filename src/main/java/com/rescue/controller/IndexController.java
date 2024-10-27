package com.rescue.controller;


import com.github.pagehelper.PageInfo;
import com.rescue.entity.*;
import com.rescue.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    AnimalService animalService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    UserService userService;

    @Autowired
    DonateService donateService;

    @Autowired
    NotifyService notifyService;


    @Autowired
    SqlyService sqlyService;

    //主页面
    @RequestMapping("/toIndex")
    public String toIndex(Model model, Animal animal,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (animal.getName() != null){
            request.setAttribute("name",animal.getName());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Animal> pageInfo = animalService.findAnimal(animal,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toIndex");
        List<Category> categoryList = categoryService.selectAll();
        model.addAttribute("categoryList",categoryList);
        return "index/index";
    }


    //主页面
    @RequestMapping("/toAnimalIndex")
    public String toAnimalIndex(Model model, Animal animal,
                          @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                          HttpSession httpSession, HttpServletRequest request){
        if (animal.getName() != null){
            request.setAttribute("name",animal.getName());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Animal> pageInfo = animalService.findAnimal(animal,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toAnimalIndex");
        List<Category> categoryList = categoryService.selectAll();
        model.addAttribute("categoryList",categoryList);
        List<Notify> notifyList = notifyService.selectAll(new HashMap());
        model.addAttribute("notifyList",notifyList);
        return "index/animal_index";
    }


    //帖子
    @RequestMapping("/toBoard")
    public String toBoard(Model model, Board Board,
                          @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                          HttpSession httpSession, HttpServletRequest request){
        if (Board.getTitle() != null){
            request.setAttribute("name",Board.getTitle());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Board> pageInfo = boardService.findBoard(Board,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toBoard");
        List<Category> categoryList = categoryService.selectAll();
        model.addAttribute("categoryList",categoryList);
        List<Notify> notifyList = notifyService.selectAll(new HashMap());
        model.addAttribute("notifyList",notifyList);
        return "index/board";
    }

    /**
     * 发表帖子
     * @return
     */
    @RequestMapping("/toIndexBoard")
    public String toIndexBoard(){
        return "index/add";
    }

    /**
     * 发表帖子
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "index/register";
    }


    /**
     * 修改帖子
     * @return
     */
    @RequestMapping("/toIndexUpdateBoard")
    public String toIndexUpdateBoard(Integer id,Model model){
        Board board = boardService.getBoardById(id);
        model.addAttribute("board",board);
        return "index/update";
    }
    /**
     * 动物详情
     * @param model
     * @param aid
     * @param httpSession
     * @param request
     * @return
     */
    @RequestMapping("/toAnimalDetail")
    public String toAnimalDetail(Model model, Integer aid,HttpSession httpSession, HttpServletRequest request){
        Animal animal = animalService.getAnimalById(aid);
        animal.setCategory(categoryService.getCategoryById(animal.getC_id()));
        model.addAttribute("animal",animal);
        Map map = new HashMap<>();
        map.put("aid",aid);
       // List<Comments> commentsList = commentsService.selectAll(map);
       // model.addAttribute("commentsList",commentsList);
        return "index/animal_detail";
    }

    /**
     * 帖子详情
     * @param model
     * @param bid
     * @param httpSession
     * @param request
     * @return
     */
    @RequestMapping("/toBoardDetail")
    public String toBoardDetail(Model model, Integer bid,HttpSession httpSession, HttpServletRequest request){
        Board board = boardService.getBoardById(bid);
        model.addAttribute("board",board);
        Map map = new HashMap<>();
        map.put("bid",bid);
        List<Comments> commentsList = commentsService.selectAll(map);
        model.addAttribute("commentsList",commentsList);
        return "index/board_detail";
    }

    /**
     * 我的帖子
     * @param model
     * @param bid
     * @param request
     * @return
     */
    @RequestMapping("/toUserIndex")
    public String toUserIndex(Model model, Integer bid, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Map map = new HashMap<>();
        map.put("uid",user!=null?user.getId():null);
        List<Board> boardList = boardService.selectAll(map);
        model.addAttribute("boardList",boardList);
        model.addAttribute("counts",boardList.size());
        return "index/user_index";
    }
    /**
     * 我的评论
     * @param model
     * @param bid
     * @param request
     * @return
     */
    @RequestMapping("/toUserComments")
    public String toUserComments(Model model, Integer bid, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Map map = new HashMap<>();
        map.put("uid",user!=null?user.getId():null);
        List<Comments> commentsList = commentsService.selectAll(map);
        model.addAttribute("commentsList",commentsList);
        model.addAttribute("counts",commentsList.size());
        return "index/user_com";
    }

    /**
     * 个人信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/toMyInfo")
    public String toMyInfo(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        User data = userService.getUserById(user.getId());
        model.addAttribute("data",data);
        return "index/set";
    }

    /**
     * 我的申请
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/myApply")
    public String myApply(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Map map = new HashMap<>();
        map.put("uid",user!=null?user.getId():null);
        List<Sqly> sqlyList = sqlyService.selectAll(map);
        for(Sqly sqly :sqlyList){
            sqly.setUser(userService.getUserById(sqly.getUid()));
            sqly.setAnimal(animalService.getAnimalById(sqly.getAid()));
        }
        model.addAttribute("sqlyList",sqlyList);
        return "index/user_sqly";
    }

    /**
     * 爱心捐赠
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/toAnimalDonate")
    public String toAnimalDonate(Model model,Integer aid, HttpServletRequest request){
        model.addAttribute("aid",aid);
        return "index/addDonate";
    }

    /**
     * 申请领养
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/toAnimalSqly")
    public String toAnimalSqly(Model model,Integer aid, HttpServletRequest request){
        model.addAttribute("aid",aid);
        return "index/sqly";
    }



}
