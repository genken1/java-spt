package ru.mirea.proxy;

import java.util.HashMap;

public class UsersDownloader {
    private DownloadableUsers api;

    public UsersDownloader(DownloadableUsers api) {
        this.api = api;
    }

    public void displayUser(String userId) {
        User user = api.getUser(userId);
        System.out.println("\n-------------------------------");
        System.out.println("User");
        System.out.println("ID: " + user.id);
        System.out.println("Name: " + user.name);
        System.out.println("Phone: " + user.dateRegistry);
        System.out.println("-------------------------------\n");
    }

    public void displayAllUsers() {
        HashMap<String, User> list = api.users();
        System.out.println("\n-------------------------------");
        System.out.println("All users");
        for (User user : list.values()) {
            System.out.println("ID: " + user.id + " / Name: " + user.name);
        }
        System.out.println("-------------------------------\n");
    }
}
