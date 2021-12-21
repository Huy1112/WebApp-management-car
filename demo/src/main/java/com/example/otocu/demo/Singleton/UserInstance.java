package com.example.otocu.demo.Singleton;

import com.example.otocu.demo.Model.New;
import com.example.otocu.demo.Model.User;

public final class UserInstance {
    private User user;
    private final static UserInstance INSTANCE = new UserInstance();

    private UserInstance() {}

    public static UserInstance getInstance() { return INSTANCE; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
