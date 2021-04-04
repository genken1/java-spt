package ru.mirea;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OwnExecutorService extends AbstractExecutorService implements ExecutorService {
    private final int capacity;
    private boolean shutdown;
    private final List<Runnable> threads;

    public OwnExecutorService(int capacity) {
        this.capacity = capacity;
        this.threads = new ArrayList<>();
    }

    @Override
    public void shutdown() {
        threads.forEach(thread -> new Thread(thread).interrupt());
        shutdown = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        this.shutdown();
        return threads;
    }

    @Override
    public boolean isShutdown() {
        return shutdown;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Future<?> submit(Runnable task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<Void> futureTask = new FutureTask<>(task, null);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public void execute(Runnable command) {
        if (command == null) throw new NullPointerException();
        if (threads.size() == capacity) throw new IllegalArgumentException("Maximum number of threads running");
        shutdown = false;

        threads.add(command);
        new Thread(command).start();
    }
}
