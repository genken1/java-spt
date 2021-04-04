package ru.mirea.proxy;

public class Main {
    public static void main(String[] args) {
        UsersDownloader naiveDownloader = new UsersDownloader(new FetchUsers());
        UsersDownloader smartDownloader = new UsersDownloader(new FetchUsersCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownloader);
        System.out.print("Time saved by caching proxy: " + (naive - smart) + "ms");

    }

    private static long test(UsersDownloader downloader) {
        long startTime = System.currentTimeMillis();

        downloader.displayAllUsers();
        downloader.displayUser("user_1");
        downloader.displayAllUsers();
        downloader.displayUser("user_2");
        downloader.displayUser("user_1");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Time elapsed: " + estimatedTime + "ms\n");
        return estimatedTime;
    }
}
