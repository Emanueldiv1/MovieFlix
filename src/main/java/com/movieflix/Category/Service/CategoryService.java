package com.movieflix.category.service;

import com.movieflix.category.repository.CategoryRepository;
import com.movieflix.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor //substitui o @Autowired, mas apenas se você estiver usando final nos atributos
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category){
        return  categoryRepository.save(category);
    }

    public Optional<Category> categoryID(Long id){
       return categoryRepository.findById(id);

    }

    public void deleteBycategory(Long id){
        categoryRepository.deleteById(id);
    }
}
