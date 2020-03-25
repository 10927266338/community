package cn.itcast.community.controller;

import cn.itcast.community.dao.UserDao;
import cn.itcast.community.dto.QuestionDTO;
import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import cn.itcast.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class IndexController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/run")
    public String hello(Model model, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userDao.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questions = questionService.findAll();
        List<User> list = userDao.findAll();
        model.addAttribute("questions",questions);
        return "index";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
