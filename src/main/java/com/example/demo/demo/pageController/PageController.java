package com.example.demo.demo.pageController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/index")
    public ModelAndView indexPage (ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping("/hello")
    public String helloPage (ModelMap modelMap) {
        modelMap.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }


    @RequestMapping("/err")
    public String errorPage (ModelMap modelMap) throws Exception {

        throw new Exception("发生错误");
//        modelMap.addAttribute("host", "http://blog.didispace.com");
//        return "error";
    }


}
