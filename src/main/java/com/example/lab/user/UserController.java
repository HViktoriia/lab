package com.example.lab.user;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
@Builder
//@ExtendWith(MockitoExtension.class)
public class UserController {

    //private UserServiceImpl userService;
    private UserMapper userMapper;
    private final UserService userService;
    private User user;

//    @GetMapping
//    public List<UserDto> getAllUsers(){
//        return userService.findAllUsers()
//                .stream()
//                .map(userMapper::toDto)
//                .toList();
@GetMapping
public List<UserDto> getAllUsers() {
//    List<String> roleNames = user.getRoles().stream()
//            .map(Role::getName)
//            .collect(Collectors.toList());
    return userService.findAllUsers()
            .stream()
            .map(user -> new UserDto(
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getContactNumber(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(Role::getName)
                            .collect(Collectors.toList())
            ))
            .toList();
    }
}
