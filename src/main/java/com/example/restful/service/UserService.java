package com.example.restful.service;

import com.example.restful.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private static List<User> userList = new ArrayList<>();
    private static int userCount = 3;

    static {
        userList.add(new User(1, "AA", new Date(), "111", "111-111"));
        userList.add(new User(2, "BB", new Date(), "222", "222-222"));
        userList.add(new User(3, "CC", new Date(), "333", "333-333"));
    }

    public List<User> findAll() {
        return userList;
    }

    public User findOne(int id) {
        for (User user : userList) {
            if (user.getId() == id) return user;
        }

        return null;
    }

    public int save(User user) {
        user.setId(++userCount);
        userList.add(user);

        return userCount;
    }
}
