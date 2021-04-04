package ru.mirea;

public class Main {
    public static void main(String[] args) {
        System.out.println("Singleton");
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());

        System.out.println("\nMultithreadingSingleton");
        System.out.println(MultithreadingSingleton.getInstance());
        System.out.println(MultithreadingSingleton.getInstance());

        System.out.println("\nDemandHolderSingleton");
        System.out.println(ru.mirea.practice_5.DemandHolderSingleton.getInstance());
        System.out.println(ru.mirea.practice_5.DemandHolderSingleton.getInstance());
    }
}
