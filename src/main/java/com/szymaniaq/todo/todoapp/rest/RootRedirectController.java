package com.szymaniaq.todo.todoapp.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootRedirectController {

    @GetMapping("/")
    public String index() {
        // Redirect root URL to the tasks listing page
        return "redirect:/tasks.html";
    }
}
