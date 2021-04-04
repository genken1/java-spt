package ru.mirea.proxy;

import java.util.HashMap;

public interface DownloadableUsers {
    HashMap<String, User> users();

    User getUser(String user);
}
