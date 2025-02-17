package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

@Controller

public class ProductController {


    private static final List<Map<String, String>> products = new ArrayList<>(List.of(
            new HashMap<>(Map.of("id", "1", "name", "TV", "description", "Best TV", "img", "/img/minitv.avif", "price", "300")),
            new HashMap<>(Map.of("id", "2", "name", "iPhone", "description", "Best iPhone", "img", "/img/iphone.webp", "price", "1000")),
            new HashMap<>(Map.of("id", "3", "name", "Chromecast", "description", "Best Chromecast", "img", "/img/cast.jpg", "price", "300")),
            new HashMap<>(Map.of("id", "4", "name", "Glasses", "description", "Best Glasses", "img", "/img/glasses.jpg", "price", "15"))
    ));


    @GetMapping("/products")

    public String index(Model model) {

        model.addAttribute("title", "Products - Online Store");

        model.addAttribute("subtitle", "List of products");

        model.addAttribute("products", products);

        return "product/index";

    }

    @GetMapping("/products/create")

    public String create(Model model) {

        model.addAttribute("title", "Create Product");

        model.addAttribute("productForm", new ProductForm());

        return "product/create";

    }


    @GetMapping("/products/{id}")

    public String show(@PathVariable String id, Model model) {

        int productId = Integer.parseInt(id) - 1;


        if (productId < 0 || productId >= products.size()) {

            return "redirect:/products";

        }


        Map<String, String> product = products.get(productId);

        model.addAttribute("title", product.get("name") + " - Online Store");

        model.addAttribute("subtitle", product.get("name") + " - Product Information");

        model.addAttribute("product", product);

        return "product/show";

    }

    @GetMapping("/created")
    public String productCreated() {
        return "product/created"; // Show the success message
    }


    @PostMapping("/products/save")
    public String save(@Valid @ModelAttribute("productForm") ProductForm productForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Create Product");
            return "product/create";
        }

        // ID autoincrementable a partir de 4
        int newId = products.size() + 4;

        Map<String, String> newProduct = new HashMap<>();

        // Asignar ID basado en el tamaño de la lista más 4
        newProduct.put("id", String.valueOf(newId));

        // Agregar nombre, descripción y precio
        newProduct.put("name", productForm.getName());
        newProduct.put("description", productForm.getDescription());  // Usar la descripción ingresada en el formulario
        newProduct.put("price", String.valueOf(productForm.getPrice()));  // Precio tal como se ingresa

        // Puedes agregar una imagen por defecto si no la estás recibiendo desde el formulario
        newProduct.put("img", "/img/default.jpg"); // O el valor adecuado si lo deseas

        // Agregar el nuevo producto a la lista
        products.add(newProduct);

        // Redirigir a la lista de productos
        return "redirect:/products/created";
    }

    @GetMapping("/products/created")
    public String productCreated(Model model) {
        model.addAttribute("message", "Product created");
        return "product/created"; // This will render the 'created' view
    }

}