package com.bookkeeping.app.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @GetMapping(value = "/s")
    public @ResponseBody String say(){
        return "111";
    }

}
