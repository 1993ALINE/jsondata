package com.atiqur_rahman.login_example.controller;

import com.atiqur_rahman.login_example.model.User;
import com.atiqur_rahman.login_example.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by salem on 23/10/2018.
 */
@Controller
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    private UserServices userServices;

    public UsersController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public String showHomePage(HttpSession httpSession,
                               Model model) {
        LOGGER.info("show Home Page");
        User user = (User) httpSession.getAttribute("user");
        Iterable<User> listOfUser = this.userServices.findAll();

        model.addAttribute("user", user);
        model.addAttribute("listOfUser", listOfUser);
        return "user_list";
    }

    @GetMapping("/users/{id}/edit")
    public String showEditUserPage(@PathVariable("id") Long id,
                                   Model model) {
        LOGGER.info("show Edit user page for user id={}", id);
        model.addAttribute("editUser", userServices.findOne(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}/edit")
    public String showEditUserPage(@PathVariable("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   RedirectAttributes redirectAttributes) {
        LOGGER.info("save Edit User with id = {}", id);
        User editUser = userServices.findOne(id);
        editUser.setEmail(email);
        editUser.setName(name);
        editUser.setPassword(password);
        this.userServices.save(editUser);

        redirectAttributes.addFlashAttribute("greenMessage", "save edit success");
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/delete")
    public String disabledUser(@PathVariable("id") Long id,
                                   RedirectAttributes redirectAttributes ){
        LOGGER.info("save Edit User with id = {}", id);
        User disabledUser = userServices.findOne(id);
        disabledUser.setDisabled(true);
        this.userServices.save(disabledUser);

        redirectAttributes.addFlashAttribute("greenMessage", "disable user  success");
        return "redirect:/users";
    }


}
