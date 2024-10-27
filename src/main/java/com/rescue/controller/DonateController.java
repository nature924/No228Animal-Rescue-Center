package com.rescue.controller;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Admin;
import com.rescue.entity.Donate;
import com.rescue.entity.Statics;
import com.rescue.entity.User;
import com.rescue.service.DonateService;
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
import java.util.List;


@Controller
public class DonateController {

    @Autowired
    DonateService service;

    //删除爱心捐赠
    @RequestMapping("/deleteDonate")
    @ResponseBody
    public String deleteDonate(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteDonate(id);
        message = "yes";
        return message;
    }

    //新增爱心捐赠
    @RequestMapping("/addDonate")
    @ResponseBody
    public String addDonate(HttpServletRequest request, Donate donate){
        String message = "no";
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            donate.setUid(user.getId());
            donate.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            service.addDonate(donate);
            message = "yes";
        }

        return message;
    }

    //修改爱心捐赠
    @RequestMapping("/updateDonate")
    @ResponseBody
    public String updateDonate(Donate donate, Model model, HttpSession httpSession){
        String message = "no";
        service.updateDonate(donate);
        message = "yes";
        return message;
    }

    //接收爱心捐赠
    @RequestMapping("/updateDonateState")
    @ResponseBody
    public String updateDonateState(Donate donate, Model model, HttpSession httpSession){
        String message = "no";
        service.updateDonateState(donate);
        message = "yes";
        return message;
    }


    //修改爱心捐赠
    @RequestMapping("/getDonateById")
    @ResponseBody
    public Donate getDonateById(Integer id, Model model, HttpSession httpSession){
        return service.getDonateById(id);
    }

    //查询爱心捐赠
    @RequestMapping("/findDonates")
    public String findallStudent(Model model, Donate donate,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (donate.getPrice() != null){
            request.setAttribute("price",donate.getPrice());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Donate> pageInfo = service.findDonate(donate,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findDonates");
        return "donate/list";
    }

    /**
     * 统计
     * @return
     */
    @RequestMapping("/selectDonatesStatics")
    @ResponseBody
    public List<Statics> selectAnimalStatics(){
        List<Statics> staticsList = service.selectDonatesStatics();
        return staticsList;
    }

}
