package com.demo.BasicAPICreation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @RequestMapping("/greet")
    @ResponseBody
    public String greet(){
        return "Greetings from Spring Boot!";
    }
}
