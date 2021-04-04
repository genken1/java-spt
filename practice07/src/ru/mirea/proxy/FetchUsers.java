package ru.mirea.proxy;

import java.util.HashMap;

public class FetchUsers implements DownloadableUsers {
    @Override
    public HashMap<String, User> users() {
        connectToServer("http://localhost:8080");
        return getUsers();
    }

    @Override
    public User getUser(String userId) {
        connectToServer("http://localhost:8080/users?" + userId);
        return getSomeUser(userId);
    }

    private int random() {
        return 5 + (int) (Math.random() * ((10 - 5) + 1));
    }

    private void experienceNetworkLatency() {
        int randomLatency = random();
        for (int i = 0; i < randomLatency; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void connectToServer(String server) {
        System.out.print("Connecting to " + server + "... ");
        // experienceNetworkLatency();
        System.out.print("Connected!" + "\n");
    }

    private HashMap<String, User> getUsers() {
        System.out.print("Downloading users... ");

        experienceNetworkLatency();
        HashMap<String, User> hmap = new HashMap<String, User>();
        hmap.put("user_1", new User("0", "Jon"));
        hmap.put("user_2", new User("1", "Steven"));
        hmap.put("user_3", new User("2", "Tom"));

        System.out.print("Done!" + "\n");
        return hmap;
    }

    private User getSomeUser(String userId) {
        System.out.print("Downloading user... ");

        // experienceNetworkLatency();
        User user = new User(userId, "Some user name");

        System.out.print("Done!" + "\n");
        return user;
    }
}
