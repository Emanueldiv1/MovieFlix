package com.movieflix.category.controller;


import com.movieflix.category.controller.request.CategoryRequest;
import com.movieflix.category.controller.response.CategoryResponse;
import com.movieflix.category.entity.Category;
import com.movieflix.category.mapper.CategoryMapper;
import com.movieflix.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor //SUB DO @Autowired
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categories = categoryService.findAllCategory()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        Category newCategory = CategoryMapper.toCategory(request);
        Category saveCategoiry = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(saveCategoiry));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> categoryId(@PathVariable Long id) {
        return categoryService.categoryID(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteBycategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
