package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;
import com.example.bookstoreapp.mapper.UserMapper;
import com.example.bookstoreapp.model.Role;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.RoleRepository;
import com.example.bookstoreapp.repository.ShoppingCartRepository;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.servive.UserService;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        checkIfUserExists(userRegistrationRequestDto);
        User newUser = userMapper.toEntity(userRegistrationRequestDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role userRole = roleRepository.findByRole(Role.RoleName.USER)
                .orElseThrow(() -> new RegistrationException("Default role not found"));
        newUser.setRoles(Set.of(userRole));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(newUser);
        User savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);
    }

    private void checkIfUserExists(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        Optional<User> optionalUser = userRepository
                .findByEmail(userRegistrationRequestDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new RegistrationException("User with such email: %s already exists"
                    .formatted(optionalUser.get().getEmail()));
        }
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new RuntimeException("User not authenticated");
        }
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

}
