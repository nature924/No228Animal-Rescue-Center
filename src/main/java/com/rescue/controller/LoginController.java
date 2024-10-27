package com.rescue.controller;

import com.rescue.entity.Admin;
import com.rescue.entity.User;
import com.rescue.service.LoginService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 登录
     * @param username
     * @param password
     * @param type
     * @param httpSession
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String loginUser( String username,String password, String type,HttpSession httpSession, Model model) throws UnsupportedEncodingException {
        Admin admin = new Admin();
        if(type !=null&& type.equals("1")){
            admin = loginService.selectAdmin(username,password);
            if (admin != null){
                httpSession.setAttribute("username",admin.getUsername());
                httpSession.setAttribute("admin",admin);
                httpSession.setAttribute("type",type);
                model.addAttribute("type",type);
                return "home/homepage";
            }else{
                model.addAttribute("status","账号或者密码输入错误！");
                return "login";
            }
        }
       else if(type.equals("2")){//用户
            User user = loginService.selectUser(username,password);
            if(user != null){
                httpSession.setAttribute("username",user.getRealname());
                httpSession.setAttribute("user",user);
                httpSession.setAttribute("type",type);
                model.addAttribute("type",type);
                return "redirect:/toIndex";


                //return "home/homepage";
            }else{
                model.addAttribute("status","账号或者密码输入错误！");
                return "login";
            }
        }else{
            model.addAttribute("status","账号或者密码输入错误！");
            return "login";
        }
    }

  /*  @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public ModelAndView addLeader(User user, String code, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        User users = loginService.selectUserByUserName(user.getUsername());
        if(users == null){
            loginService.addUser(user);
            modelAndView.addObject("status","注册成功");
            modelAndView.setViewName("login");
        }else{
            modelAndView.addObject("status","用户名重复！");
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }*/


    /**
     * 登出
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/outLogin",method = RequestMethod.GET)
    public String outLogin(HttpSession httpSession){
        httpSession.invalidate();
        return "login";
    }
    @RequestMapping(value = "/toConsole")
    public String toConsole() {
        return "console";
    }

    @RequestMapping(value = "/toMap")
    public String toMap( ) {
        return "map/baidu";
    }

    @RequestMapping(value = "/toMapAdmin")
    public String toMapAdmin( ) {
        return "map/baiduAdmin";
    }

    @RequestMapping(value = "/echart")
    public String echart( ) {
        return "son/echart";
    }

    @RequestMapping(value = "/toUpdateAdmin")
    public String toUpdateAdmin( ) {
        return "admin/admin";
    }


    //修改管理员
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public String updateAdmin(String newPass,String password, Model model, HttpSession httpSession){
        Admin aa = (Admin)(httpSession.getAttribute("admin"));
        String message = "no";
        if(aa!=null && aa.getPassword().equals(password)){
            aa.setPassword(newPass);
            loginService.updateAdmin(aa);
            message = "yes";
        }
        return message;
    }



    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\upload\\" + dateStr+"\\"+uuid+"." + prefix;

                File files=new File(filepath);
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (Exception e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }



}
