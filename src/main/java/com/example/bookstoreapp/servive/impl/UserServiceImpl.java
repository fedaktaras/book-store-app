package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;
import com.example.bookstoreapp.mapper.UserMapper;
import com.example.bookstoreapp.model.Role;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.RoleRepository;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.servive.UserService;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with such email already exists");
        }
        User newUser = userMapper.registerUserDtoToUser(userRegistrationRequestDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role userRole = roleRepository.findByRole(Role.RoleName.USER)
                .orElseThrow(() -> new RegistrationException("Default role not found"));
        newUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        newUser.setRoles(Set.of(userRole));
        User savedUser = userRepository.save(newUser);
        return userMapper.userToUserResponseDto(savedUser);
    }
}
