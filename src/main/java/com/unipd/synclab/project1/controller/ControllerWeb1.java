package com.unipd.synclab.project1.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.unipd.synclab.project1.model.User;
import com.unipd.synclab.project1.tmp_db.TmpDb;


@RestController
public class ControllerWeb1 {
    @Autowired
    TmpDb tmpDb;

    @GetMapping("")
    public String hello(){
        return "hello world";
    }

    @GetMapping("user")
    public User getUser(@RequestParam Integer userId) throws Exception {
        User  user =  tmpDb.getUser(userId);
        if(user != null) 
            return user;
        throw new Exception("Utente non trovato nella lista con id = " + userId);
    }

    @GetMapping("users")
    public List<User> getMethodName() {
        return tmpDb.getAllUsers();
    }
    

    
    @PostMapping("user")
    public Integer addUser(@RequestBody User entity) {
        tmpDb.addUser(entity);
        return entity.getId();
    }
    
    
    
}
