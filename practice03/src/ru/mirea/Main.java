package ru.mirea;


public class Main {
    public static void main(String[] args) throws Exception {
        SyncSet<Integer> set = new SyncSet<>();
        SyncList<Integer> list = new SyncList<>();
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                set.add(i);
                list.add(i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                set.add(i);
                list.add(i);
            }
        });

        one.start();
        two.start();
        // Thread.sleep(1000);
        set.forEach(item -> System.out.print(item+" "));
        System.out.println();
        list.forEach(item -> System.out.print(item+" "));

    }
}
