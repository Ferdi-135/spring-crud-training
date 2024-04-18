package ch.benedict.m223.lektion4.springjpademo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("student/management")
    public String showFrom(){
        return "crud_demo";
    }

}
