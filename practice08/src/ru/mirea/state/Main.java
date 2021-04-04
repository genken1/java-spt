package ru.mirea.state;

import ru.mirea.state.ui.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        System.out.println(
                player.getState().onPlay() + "\n" +
                        player.getState().onLock() + "\n" +
                        player.getState().onPlay() + "\n" +
                        player.getState().onPlay() + "\n" +
                        player.getState().onNext() + "\n" +
                        player.getState().onPrevious() + "\n" +
                        player.getState().onPrevious()
        );
    }
}
