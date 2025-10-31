package it.maelstrom.tabula.controller;

import it.maelstrom.tabula.common.constant.EndpointConstants;
import it.maelstrom.tabula.service.CategoryService;
import it.maelstrom.tabula.web.response.CategoryResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndpointConstants.CATEGORY_BASE_URL)
@Validated
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public void createCategory(@RequestParam @NotBlank(
            message = "Category name is blank"
    ) String categoryName) {
        this.service.createCategory(categoryName);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok().body(this.service.getCategories());
    }

}
