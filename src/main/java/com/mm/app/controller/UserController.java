package com.mm.app.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mm.app.model.User;
import com.mm.app.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
	UserService userService;

    @Autowired
    MessageSource messageSource;
	
	/*
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "allusers";
    }

    /*
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [name] should be implementing custom @Unique annotation
         * and applying it on field [name] of Model class [User].
         *
         *
         */
        if(!userService.isUserNameUnique(user.getId(), user.getName())){
            FieldError nameUniqueError =new FieldError("user","name",messageSource.getMessage("non.unique.username", new String[]{user.getName()}, Locale.getDefault()));
            result.addError(nameUniqueError);
            return "registration";
        }

        userService.createUser(user);

        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }


    /*
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, ModelMap model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                                 ModelMap model, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "registration";
        }

        if(!userService.isUserNameUnique(user.getId(), user.getName())){
            FieldError nameUniqueError =new FieldError("user","name",messageSource.getMessage("non.unique.username", new String[]{user.getName()}, Locale.getDefault()));
            result.addError(nameUniqueError);
            return "registration";
        }

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getName()  + " updated successfully");
        return "success";
    }


    /*
     * This method will delete an user by it's name value.
     */
    @RequestMapping(value = { "/delete-{id}-user" }, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/list";
    }
}
