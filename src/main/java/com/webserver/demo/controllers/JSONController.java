package com.webserver.demo.controllers;


import com.webserver.demo.models.SQLQueries;
import com.webserver.demo.models.Workwishfiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webserver.demo.models.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.time.LocalDateTime;

@RestController
public class JSONController {

    @GetMapping(path = "/getUser")
    public ResponseEntity<?> LoginPage(@RequestParam("login") String login){
        try{User user = SQLQueries.Select(login);
            Workwishfiles.FileWriter(user);
            return ResponseEntity.ok(user.toString());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @GetMapping(path = "/getString")
    public ResponseEntity<?> ReadString() {
            return ResponseEntity.ok(Workwishfiles.FileReader());
    }
    /*
   //@PostMapping("/postUser")
   //public ResponseEntity<?> Authenticate(@Valid @RequestBody User user){
   //     return ResponseEntity.ok(user.toString());
   // }
   */

    @PostMapping("/post")
    public ResponseEntity<String> Testing(@RequestBody Map<String,String> request){
        try{
            if(request.size()>2 || !request.containsKey("login") || !request.containsKey("password") || request.containsValue("")){
                throw new Exception("Bad JSON!");
            }
            User user = new User(request.get("login"),request.get("password"));
            int upd = SQLQueries.Insert(user);
            return ResponseEntity.ok("{" +
                    "\"date\":\"" + LocalDateTime.now() + '\"' +
                    "\"stringsupd\":\"" + upd + '\"' +
                    '}');
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}