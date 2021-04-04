package ru.mirea;

public class DemandHolderSingleton {
    private DemandHolderSingleton(){}

    private static class SingletonHolder {
        private final static DemandHolderSingleton instance = new DemandHolderSingleton();
    }

    public static DemandHolderSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
