package com.movieflix.Category.Service;

import com.movieflix.Category.Repository.CategoryRepository;
import com.movieflix.Category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor //substitui o @Autowired, mas apenas se você estiver usando final nos atributos
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }
}
