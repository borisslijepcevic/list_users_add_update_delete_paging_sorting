package com.boris.list_users_add_update_delete_pagging_sorting.controller;

import com.boris.list_users_add_update_delete_pagging_sorting.entity.User;
import com.boris.list_users_add_update_delete_pagging_sorting.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    private UserService userService;

    public AdminController(UserService theUserService){
        userService = theUserService;
    }


    @GetMapping("/list")
    public String findAll(Model theModel){
        return findPaginated(1, "firstName", "asc", theModel);

    }

    @GetMapping("/showFormForAddUser")
    public String showFormForAddUser(Model theModel){
        User theUser = new User();

        theModel.addAttribute("user",  theUser);

        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User theUser){
        userService.saveUser(theUser);
        return "redirect:/list";
    }

    @GetMapping("/showFormForUpdateUser")
    public String showFormForUpdateUser(@RequestParam("userId") int theId,
                                        Model theModel){
        User theUser = userService.findById(theId);

        theModel.addAttribute("user", theUser);

        return "user-form";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int theId){
        userService.deleteById(theId);
        return "redirect:/list";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model theModel){
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> users = page.getContent();
        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", page.getTotalPages());
        theModel.addAttribute("totalItems", page.getTotalElements());
        theModel.addAttribute("sortField", sortField);
        theModel.addAttribute("sortDir", sortDir);
        theModel.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        theModel.addAttribute("users", users);
        return "list-users";
    }
}
