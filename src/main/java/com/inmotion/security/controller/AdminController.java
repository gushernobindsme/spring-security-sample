package com.inmotion.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by takumiera on 2017/07/31.
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @RequestMapping
    public String index() {
        return "admin";
    }
}
