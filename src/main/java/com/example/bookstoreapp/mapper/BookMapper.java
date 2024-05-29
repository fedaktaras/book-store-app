package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CategoryMapper.class)
public interface BookMapper {

    BookDto toDtoBook(Book book);

    @Mapping(source = "categoryIds", target = "categories")
    Book toBook(BookRequestDto bookRequestDto);

    @Mapping(source = "categoryIds", target = "categories")
    Book toBook(BookUpdateDto bookUpdateDto);

    @Mapping(source = "categoryIds", target = "categories")
    void updateBookFromDto(BookUpdateDto dto, @MappingTarget Book book);

    default Set<Category> map(Set<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }
}
