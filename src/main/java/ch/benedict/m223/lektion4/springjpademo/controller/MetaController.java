package ch.benedict.m223.lektion4.springjpademo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MetaController {

    @RequestMapping("status")
    public String isAlive(){
        return "I am alive!";
    }

    @RequestMapping("ping")
    public String pong(){
        return "pong";
    }

}
