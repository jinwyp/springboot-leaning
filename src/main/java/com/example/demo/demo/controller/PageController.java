package com.example.demo.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

}
