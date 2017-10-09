package com.inmotion.security.controller;

import com.inmotion.security.utils.MessageResourceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping({"/", "/login"})
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            MessageResourceHelper.addErrorMessage(messageSource, model, "error.authentication_fail");
        }
        return "login";
    }

}
