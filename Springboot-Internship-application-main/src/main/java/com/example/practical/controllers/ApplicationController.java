package com.example.practical.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.practical.model.Application;
import com.example.practical.service.ApplicationService;

@Controller
public class ApplicationController {
    @Autowired
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String redirectToApplications() {
        return "redirect:/applications";
    }

    @GetMapping("/applications")
    public String home(Model model) {
        model.addAttribute("applications", service.getAll());
        return "index";
    }

    @GetMapping("/application/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("application", service.getById(id).orElse(null));
        return "view_application";
    }

    @GetMapping("/applications/status/approved")
    public String approved(Model model) {
        model.addAttribute("applications", service.getApproved());
        return "index";
    }

    @GetMapping("/application/add")
    public String showAddForm(Model model) {
        model.addAttribute("application", new Application());
        model.addAttribute("formAction", "/application"); // ✅ default POST URL
        return "add_application";
    }

    @PostMapping("/application")
    public String save(@ModelAttribute Application application) {
        service.save(application);
        return "redirect:/applications";
    }

    @GetMapping("/application/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("application", service.getById(id).orElse(null));
        return "update_application";
    }

    @PostMapping("/application/update")
    public String update(@ModelAttribute Application application) {
        service.update(application);
        return "redirect:/applications";
    }

    @GetMapping("/application/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/applications";
    }
}
