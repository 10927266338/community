package cn.itcast.community.controller;

import cn.itcast.community.dao.QuestionDao;
import cn.itcast.community.dao.UserDao;
import cn.itcast.community.domain.Question;
import cn.itcast.community.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title")String title,
                            @RequestParam(name = "description")String description,
                            @RequestParam(name = "tag")String tag,
                            HttpServletRequest request, Model model
                            ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (StringUtils.isEmpty(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(description)){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user=null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userDao.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtModified(user.getGmtModified());
        questionDao.save(question);
        return "redirect:/hello/run";
    }
}
