package cn.itcast.community.controller;

import cn.itcast.community.dto.QuestionDTO;
import cn.itcast.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        QuestionDTO questionDTO = questionService.findAll(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
