package com.webserver.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webserver.demo.models.User;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class JSONController {

    @GetMapping("/getUser")
    public ResponseEntity<?> LoginPage() throws Exception {
        return ResponseEntity.ok(new User("aaa", "bbb"));
    }

    @PostMapping("/postUser")
    public ResponseEntity<?> Authenticate(@Valid @RequestBody User user){
        return ResponseEntity.ok(user.toString());
    }

    @PostMapping("/post")
    public ResponseEntity<?> Testing(@RequestBody Map<String,String> request){
        try{
            if(request.size()>2 || !request.containsKey("login") || !request.containsKey("password") || request.containsValue("")){
                throw new Exception("Bad JSON!");
            }
            User user = new User(request.get("login"),request.get("password"));
            return ResponseEntity.ok(user.toString());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}