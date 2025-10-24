package com.movieflix.Category.Controller;


import com.movieflix.Category.Service.CategoryService;
import com.movieflix.Category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor //SUB DO @Autowired
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> getAllCategory(){
        return categoryService.findAllCategory();
    }


}
