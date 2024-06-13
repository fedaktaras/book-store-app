package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.CartItemRequestDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(source = "bookId", target = "book.id")
    CartItem toEntity(CartItemDto cartItemDto);

    @Mapping(source = "bookId", target = "book.id")
    CartItem toEntity(CartItemRequestDto cartItemRequestDto);

    default Book map(Long bookId) {
        if (bookId == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookId);
        return book;
    }
}
