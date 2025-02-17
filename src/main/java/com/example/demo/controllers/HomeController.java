package com.example.demo.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to Spring Boot");
        model.addAttribute("subtitle", "A Spring Boot EAFIT App");

        // Información de contacto para el footer
        model.addAttribute("email", "contact@myapp.com");
        model.addAttribute("phone", "+123 456 7890");
        model.addAttribute("address", "123 Spring Boot Street, Bogotá");

        return "home/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Sobre Nosotros - Online Store");
        model.addAttribute("subtitle", "Sobre Nosotros");
        model.addAttribute("description", "Esta es una página informativa sobre nuestra aplicación.");
        model.addAttribute("author", "Desarrollado por: Julian Mejia");
        return "home/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contacto - Online Store");
        model.addAttribute("subtitle", "Contacto");
        return "home/contact";
    }

}