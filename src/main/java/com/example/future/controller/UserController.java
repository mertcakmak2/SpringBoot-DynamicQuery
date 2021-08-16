package com.example.future.controller;

import com.example.future.filter.FilterRequest;
import com.example.future.model.User;
import com.example.future.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/filter")
    private List<User> filterUsers(@RequestBody List<FilterRequest> filterRequests, @RequestParam String filterType){
        return userService.filterUsers(filterRequests, filterType);
    }
}
