package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;
import com.example.bookstoreapp.mapper.UserMapper;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.servive.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with such email already exists");
        }
        User newUser = userMapper.registerUserDtoToUser(userRegistrationRequestDto);
        User savedUser = userRepository.save(newUser);
        return userMapper.userToUserResponseDto(savedUser);
    }
}
