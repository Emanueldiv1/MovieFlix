package com.movieflix.category.mapper;

import com.movieflix.category.controller.dtos.request.CategoryRequest;
import com.movieflix.category.controller.dtos.response.CategoryResponse;
import com.movieflix.category.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
    public static Category toCategory(CategoryRequest categoryRequest){
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
