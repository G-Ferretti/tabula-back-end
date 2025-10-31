package it.maelstrom.tabula.service;

import it.maelstrom.tabula.model.Category;
import it.maelstrom.tabula.repository.CategoryRepository;
import it.maelstrom.tabula.web.exception.MissingResourceException;
import it.maelstrom.tabula.web.response.CategoryResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public void createCategory(String categoryName) {
        Category category = Category.builder().name(categoryName).build();
        repository.save(category);
    }

    public List<CategoryResponse> getCategories() {
        List<Category> categories = this.repository.findAll();



        return categories
                .stream()
                .map((category) -> CategoryResponse
                        .builder()
                        .categoryId(category.getId())
                        .categoryName(category.getName())
                        .build()
                ).toList();
    }

}