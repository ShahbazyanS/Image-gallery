package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Image;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        List<Category> all = categoryService.findAll();
        model.addAttribute("all", all);
        return "admin";
    }

    @PostMapping("/addImage")
    public String addImage(@ModelAttribute Image images, @RequestParam("image") MultipartFile file) throws IOException{
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File img = new File(uploadDir, name);
        file.transferTo(img);
        images.setPicUrl(name);
        images.setDate(LocalDateTime.now());
       imageService.save(images);
        return "redirect:/admin";
    }


    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        category.setPicUrl(name);
        categoryService.save(category);
        return "redirect:/admin";
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
}
