package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import com.example.bookstoreapp.dto.CategoryDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CategoryMapper.class)
public abstract class BookMapper {
    @Autowired
    protected CategoryMapper categoryMapper;

    public abstract BookDto toDtoBook(Book book);

    public abstract Book toBook(BookRequestDto bookRequestDto);

    public abstract Book toBook(BookUpdateDto bookUpdateDto);

    @Mapping(source = "categoryIds", target = "categories")
    public abstract void updateBookFromDto(BookUpdateDto dto, @MappingTarget Book book);

    public Set<Long> mapCategoriesToCategoryIds(Set<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
    }

    public Set<Category> mapCategoryIdsToCategories(Set<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }

    public Set<CategoryDto> mapCategoriesToCategoryDtos(Set<Category> categories) {
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toSet());
    }

    public Set<Category> mapCategoryDtosToCategories(Set<CategoryDto> categoryDtos) {
        return categoryDtos.stream()
                .map(categoryMapper::toEntity)
                .collect(Collectors.toSet());
    }
}
