package com.rescue.controller;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Category;
import com.rescue.service.CategoryService;
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

/**
 * @author hxh
 * @version V1.00
 * @date 2022/2/13 18:10
 * @since V1.00
 */

@Controller
public class CategoryController {

    @Autowired
    CategoryService service;

    //删除动物类别
    @RequestMapping("/deleteCategory")
    @ResponseBody
    public String deleteCategory(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteCategory(id);
        message = "yes";
        return message;
    }

    //新增动物类别
    @RequestMapping("/addCategory")
    @ResponseBody
    public String addCategory(Category category,Model model,HttpSession httpSession){
        String message = "no";
        service.addCategory(category);
        message = "yes";
        return message;
    }

    //修改动物类别
    @RequestMapping("/updateCategory")
    @ResponseBody
    public String updateCategory(Category category, Model model, HttpSession httpSession){
        String message = "no";
        service.updateCategory(category);
        message = "yes";
        return message;
    }


    //修改动物类别
    @RequestMapping("/getCategoryById")
    @ResponseBody
    public Category getCategoryById(Integer id, Model model, HttpSession httpSession){
        return service.getCategoryById(id);
    }

    //查询动物类别
    @RequestMapping("/findCategorys")
    public String findallStudent(Model model, Category category,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (category.getCname() != null){
            request.setAttribute("key",category.getCname());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Category> pageInfo = service.findCategory(category,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findCategorys");
        return "category/list";
    }
    
}
