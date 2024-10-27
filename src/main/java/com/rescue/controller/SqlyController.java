package com.rescue.controller;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Admin;
import com.rescue.entity.Animal;
import com.rescue.entity.Sqly;
import com.rescue.entity.User;
import com.rescue.service.AnimalService;
import com.rescue.service.SqlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class SqlyController {

    @Autowired
    SqlyService service;

    @Autowired
    AnimalService animalService;

    //删除领养
    @RequestMapping("/deleteSqly")
    @ResponseBody
    public String deleteSqly(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteSqly(id);
        message = "yes";
        return message;
    }

    //新增领养
    @RequestMapping("/addSqly")
    @ResponseBody
    public String addSqly(HttpServletRequest request, Sqly sqly){
        String message = "no";
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            Map map = new HashMap<>();
            map.put("aid",sqly!=null?sqly.getAid():null);
            List<Sqly> sqlyList = service.selectAll(map);
            if(sqlyList ==null || sqlyList.size() == 0){
                sqly.setUid(user.getId());
                sqly.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                sqly.setSno(getRno());
                service.addSqly(sqly);
                message = "yes";
            }
        }
        return message;
    }

    public String getRno() {//随机生成
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;

    }
    //修改领养
    @RequestMapping("/updateSqly")
    @ResponseBody
    public String updateSqly(Sqly sqly, Model model, HttpSession httpSession){
        String message = "no";
        service.updateSqly(sqly);
        message = "yes";
        return message;
    }

    //审批领养
    @RequestMapping("/updateSqlyState")
    @ResponseBody
    public String updateSqlyState(Sqly sqly, Model model, HttpSession httpSession){
        String message = "no";
        service.updateSqlyState(sqly);
        if(sqly.getState().equals("1")){
            Sqly sqly1 = service.getSqlyById(sqly.getId());
            Animal animal = animalService.getAnimalById(sqly1.getAid());
            animal.setStatus("1");
            animalService.updateState(animal);
        }
        message = "yes";
        return message;
    }


    //修改领养
    @RequestMapping("/getSqlyById")
    @ResponseBody
    public Sqly getSqlyById(Integer id, Model model, HttpSession httpSession){
        return service.getSqlyById(id);
    }

    //查询领养
    @RequestMapping("/findSqlys")
    public String findallStudent(Model model, Sqly sqly,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (sqly.getSno() != null){
            request.setAttribute("sno",sqly.getSno());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Sqly> pageInfo = service.findSqly(sqly,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findSqlys");
        return "sqly/list";
    }
    
}
