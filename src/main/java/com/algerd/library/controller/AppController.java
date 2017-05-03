package com.algerd.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title", "Library");

        return "index";
    }

    @GetMapping("/readers")
    String reader(ModelMap modal) {
        modal.addAttribute("title", "Readers");

        return "readers";
    }

    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }

}
