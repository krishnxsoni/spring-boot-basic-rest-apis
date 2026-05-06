package com.example.test.controller;

import com.example.test.entities.User;
import com.example.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserConroller {

    @Autowired
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserConroller.class);

    @GetMapping("/users") // <-- RequestMapping + METHOD_TYPE.GET == GetMapping
    public List<User> getUsers()
    {
        log.info(":: Inside getUsers() :: ");
        List<User> usersList  =userService.getAllUsers();
        log.info("usersList :: {}",usersList);
        return usersList;
    }

    @GetMapping("/users/{username}") // <-- RequestMapping + METHOD_TYPE.GET == GetMapping
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username)
    {
        log.info(":: Inside getUserByUsername() :: ");
        User user = userService.getUserByUsername(username);
        if(user==null){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);//contentType(MediaType.APPLICATION_JSON).body(null);
        }
        else{
            log.info("user :: "+user);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
        }
    }

    @PostMapping("/users/newuser") // <-- RequestMapping + METHOD_TYPE.POST == PostMapping
    public ResponseEntity addUsers(@RequestBody User user){
        log.info(":: Inside addUsers() :: ");
        return userService.addNewUsers(user);
    }

    @DeleteMapping("/users/removeuser/{username}") // <-- RequestMapping + METHOD_TYPE.DELETE == DeleteMapping
    public ResponseEntity removeUser(@PathVariable("username") String username){
        log.info(":: Inside removeUser() :: ");
        return userService.removeUserByUsername(username);
    }

    @PutMapping("/users/updateuser/{id}") // <-- RequestMapping + METHOD_TYPE.PUT == PutMapping
    public ResponseEntity updateUser(@PathVariable("id") int id,@RequestBody String requestBody){
        log.info(":: Inside updateUser() :: ");
        return userService.updateUserById(id,requestBody);
    }
}
