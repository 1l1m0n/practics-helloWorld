package com.antonovproject.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "Hello Antonov!!!";
    }
}
