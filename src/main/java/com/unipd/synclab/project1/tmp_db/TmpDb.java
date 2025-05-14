package com.unipd.synclab.project1.tmp_db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.unipd.synclab.project1.model.User;

@Component
public class TmpDb {
    private  List<User> users  = new ArrayList<>();

    public  User getUser(Integer id){
        for(User u:users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
    public  List<User> getAllUsers(){
        return users;
    }
    public  void addUser(User u){
        users.add(u);
    }
}
