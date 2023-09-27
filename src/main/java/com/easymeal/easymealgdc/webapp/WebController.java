package com.easymeal.easymealgdc.webapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class WebController {

    @RequestMapping(value = "/sign-up")
    public ModelAndView signUp() {

        ModelAndView modelAndView = new ModelAndView("sign-up");
        return modelAndView;
    }
    @RequestMapping(value = "/sign-in")
    public ModelAndView signIn() {

        ModelAndView modelAndView = new ModelAndView("sign-in");
        return modelAndView;
    }

}
