package com.example.lab.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserController {

    private UserServiceImpl userService;
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
