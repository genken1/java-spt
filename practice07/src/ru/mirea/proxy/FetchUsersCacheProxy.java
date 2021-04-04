package ru.mirea.proxy;

import java.util.HashMap;

public class FetchUsersCacheProxy implements DownloadableUsers {
    private final DownloadableUsers userService;
    private HashMap<String, User> cacheAllUsers = new HashMap<String, User>();
    private final HashMap<String, User> cacheUser = new HashMap<String, User>();

    public FetchUsersCacheProxy() {
        this.userService = new FetchUsers();
    }

    @Override
    public HashMap<String, User> users() {
        if (cacheAllUsers.isEmpty()) {
            cacheAllUsers = userService.users();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cacheAllUsers;
    }

    @Override
    public User getUser(String userId) {
        User user = cacheUser.get(userId);
        if (user == null) {
            user = userService.getUser(userId);
            cacheUser.put(userId, user);
        } else {
            System.out.println("Retrieved user '" + userId + "' from cache.");
        }
        return user;
    }
}
