package cn.itcast.community.controller;

import cn.itcast.community.dao.UserDao;
import cn.itcast.community.dto.PaginationDTO;
import cn.itcast.community.dto.QuestionDTO;
import cn.itcast.community.model.User;
import cn.itcast.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class IndexController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/run")
    public String hello(Model model, HttpServletRequest request,
                        @RequestParam(name = "page",defaultValue = "0")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size
                        ){


        PaginationDTO paginationDTO= questionService.findAll(page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }
    @RequestMapping("/esc")
    public String esc(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setValue(null);
        response.addCookie(cookie);
        return "redirect:/hello/run";
    }
}
