package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.CategoryDto;
import com.example.bookstoreapp.dto.CreateCategoryDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto save(CreateCategoryDto createCategoryDto);

    void deleteById(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    List<BookDto> getBooksByCategoryId(Long id, Pageable pageable);
}
