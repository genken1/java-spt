package ru.mirea;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new OwnExecutorService(2);

        Future<String> task = executorService.submit(() -> "Some string from thread");
        executorService.shutdown();

        if (task.isDone() && !task.isCancelled()) {
            String sub = task.get();
            System.out.println("Future result: " + sub);
        }

        executorService.execute(() -> {
            System.out.println("Second thread interrupted");
        });

        try {
            executorService.submit(() -> "Third thread does not start");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
