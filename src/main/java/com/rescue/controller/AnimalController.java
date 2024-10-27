package com.rescue.controller;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Animal;
import com.rescue.entity.Category;
import com.rescue.entity.Statics;
import com.rescue.service.AnimalService;
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
import java.util.List;


@Controller
public class AnimalController {

    @Autowired
    AnimalService service;

    @Autowired
    CategoryService categoryService;

    //删除动物
    @RequestMapping("/deleteAnimal")
    @ResponseBody
    public String deleteAnimal(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.deleteAnimal(id);
        message = "yes";
        return message;
    }

    //新增动物
    @RequestMapping("/addAnimal")
    @ResponseBody
    public String addAnimal(Animal animal,Model model,HttpSession httpSession){
        String message = "no";
        animal.setCreate_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        service.addAnimal(animal);
        message = "yes";
        return message;
    }

    //修改动物
    @RequestMapping("/updateAnimal")
    @ResponseBody
    public String updateAnimal(Animal animal, Model model, HttpSession httpSession){
        String message = "no";
        service.updateAnimal(animal);
        message = "yes";
        return message;
    }


    //查询动物
    @RequestMapping("/getAnimalById")
    @ResponseBody
    public Animal getAnimalById(Integer id, Model model, HttpSession httpSession){
        return service.getAnimalById(id);
    }

    //查询动物
    @RequestMapping("/findAnimals")
    public String findallStudent(Model model, Animal animal,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        if (animal.getName() != null){
            request.setAttribute("name",animal.getName());
        }
        httpSession.setAttribute("pageNum",pageNum);
        httpSession.setAttribute("pageSize",pageSize);
        PageInfo<Animal> pageInfo = service.findAnimal(animal,pageNum,pageSize);
        if (!(pageInfo.getSize() > 0)){
            model.addAttribute("status","暂无记录");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","findAnimals");
        List<Category> categoryList = categoryService.selectAll();
        model.addAttribute("categoryList",categoryList);
        return "animal/list";
    }

    /**
     * 统计
     * @return
     */
    @RequestMapping("/selectAnimalStatics")
    @ResponseBody
    public List<Statics> selectAnimalStatics(){
        List<Statics> staticsList = service.selectAnimalStatics();
        return staticsList;
    }

    /**
     * 统计
     * @return
     */
    @RequestMapping("/selectAnimalStaticsByCate")
    @ResponseBody
    public List<Statics> selectAnimalStaticsByCate(){
        List<Statics> staticsList = service.selectAnimalStaticsByCate();
        return staticsList;
    }
    
}
