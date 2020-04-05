package cn.itcast.community.controller;

import cn.itcast.community.dao.QuestionDao;
import cn.itcast.community.dao.UserDao;
import cn.itcast.community.model.Question;
import cn.itcast.community.model.User;
import cn.itcast.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;


    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }

    @RequestMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,Model model){
        Question question = questionService.findQuestion(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        questionService.save(question);
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title")String title,
                            @RequestParam(name = "description")String description,
                            @RequestParam(name = "tag")String tag,
                            @RequestParam(name = "id")Integer id,
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
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        questionService.save(question);
        return "redirect:/hello/run";
    }
}
