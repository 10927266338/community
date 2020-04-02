package cn.itcast.community.controller;

import cn.itcast.community.dao.QuestionDao;
import cn.itcast.community.dao.UserDao;
import cn.itcast.community.dto.PaginationDTO;
import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import cn.itcast.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionService questionService;
    @RequestMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model, HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "0")Integer page,
                          @RequestParam(name = "size",defaultValue = "5")Integer size){

        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "redirect:/hello/run";
        }
        if (action.contains("question")){
            model.addAttribute("sectionName","我的问题");
            model.addAttribute("section","questions");
        }else  if(action.contains("repies")){
            model.addAttribute("sectionName","最新消息");
            model.addAttribute("section","repies");
        }
        PaginationDTO paginationDTO = questionService.findAll(user.getId(), page, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
}
