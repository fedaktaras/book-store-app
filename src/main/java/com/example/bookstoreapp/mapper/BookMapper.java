package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto bookToBookDto(Book book);

    Book createBookRequestDtoToBook(BookRequestDto bookRequestDto);

    void updateBookFromDto(BookDto bookDto, @MappingTarget Book book);
}
