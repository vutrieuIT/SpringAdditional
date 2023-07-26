package com.example.springadditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("")
    public User getUser(@RequestParam Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping
    public ResponseEntity<String> deteleUser(@RequestParam Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("deleted user");
    }


}
