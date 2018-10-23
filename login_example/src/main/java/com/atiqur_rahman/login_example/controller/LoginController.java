package com.atiqur_rahman.login_example.controller;

import com.atiqur_rahman.login_example.exception.UserAlreadyRegisterException;
import com.atiqur_rahman.login_example.exception.UserNotFoundException;
import com.atiqur_rahman.login_example.model.User;
import com.atiqur_rahman.login_example.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by salem on 23/10/2018.
 */

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private UserServices userServices;

    public LoginController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/login")
    public String showLoginPage(){
        LOGGER.info("Show Login page");
        return "login";
    }

    @PostMapping("/login")
    public String loginProccess(@RequestParam("email")String email,
                                @RequestParam("password")String password,
                                RedirectAttributes redirectAttributes,
                                HttpSession httpSession){

        User user=null;
        try {
           user= this.userServices.findUserByEmailAndPassword(email,password);
        } catch (UserNotFoundException e) {
            LOGGER.error("User not found email={}  and password={}",email,password);
            redirectAttributes.addFlashAttribute("redMessage","User Not Found ");
            return "redirect:/login";
        }

        httpSession.setAttribute("user",user);
        return "redirect:/users";
    }

}
