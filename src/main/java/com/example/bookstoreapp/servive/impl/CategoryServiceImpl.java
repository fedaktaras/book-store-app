package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.CategoryDto;
import com.example.bookstoreapp.dto.CreateCategoryDto;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.mapper.CategoryMapper;
import com.example.bookstoreapp.model.Category;
import com.example.bookstoreapp.repository.CategoryRepository;
import com.example.bookstoreapp.servive.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               BookMapper bookMapper, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .stream().map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category not found with id: " + id));
    }

    @Override
    public CategoryDto save(CreateCategoryDto createCategoryDto) {
        Category category = categoryMapper.toEntity(createCategoryDto);
        Category saveCategory = categoryRepository.save(category);
        return categoryMapper.toDto(saveCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        categoryRepository.findById(id)
                .orElseThrow(() ->
                new EntityNotFoundException("Category not found with id: " + id));
        Category category = categoryMapper.toEntity(categoryDto);
        category.setId(id);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getBooksByCategoryId(Long id, Pageable pageable) {
        return categoryRepository.findAllBooksWithCategory(id, pageable)
                .stream().map(bookMapper::toDtoBook)
                .toList();
    }
}
