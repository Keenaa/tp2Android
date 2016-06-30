package com.example.meryl.tp2;

public class Session {
    private static final Session INSTANCE = new Session();
    private User currentUser;

    private Session() {
    }

    public static Session getInstance() {
        return INSTANCE;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
