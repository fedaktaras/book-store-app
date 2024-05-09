package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.UserResponseDto;
import com.example.bookstoreapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User registerUserDtoToUser(UserRegistrationRequestDto dto);

    UserResponseDto userToUserResponseDto(User user);
}
