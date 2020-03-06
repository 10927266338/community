package cn.itcast.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/run")
    public String hello(@RequestParam(name = "name")String name, Model model){
        model.addAttribute("name",name);
        System.out.println("hello");
        return "success";
    }
}
