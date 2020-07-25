package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Image;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final ImageService imageService;


    @GetMapping("/")
    public String homePage(ModelMap map){
        List<Category> categories = categoryService.findAll();
        map.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/imageByCategory")
    public String imageByCategory(@RequestParam("id") int id, ModelMap model){
        List<Image> images = imageService.grtImageByCategory(id);
        model.addAttribute("images", images);
        return "images";
    }

    @GetMapping("/successeLogin")
    public String successeLogin(){
        return "redirect:/adminPage";
    }

    @GetMapping("loginPage")
    public String loginPage(){
        return "loginPage";
    }
}
