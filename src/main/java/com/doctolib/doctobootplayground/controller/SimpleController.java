package com.doctolib.doctobootplayground.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {
    private final String message;

    public SimpleController(
            @Value("#{'${welcome.message:Welcome to your awesome new Doctoboot service!}'}") String message) {
        // inject via application.properties
        this.message = message;
    }

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("serviceName", "doctoboot-playground");
        model.addAttribute("message", message);
        return "index";
    }
}
