package ru.mirea;

public class MultithreadingSingleton {
    private static MultithreadingSingleton instance = new MultithreadingSingleton();

    private MultithreadingSingleton(){}

    public static MultithreadingSingleton getInstance() {
        return instance;
    }
}
