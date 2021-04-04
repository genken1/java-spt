package ru.mirea.composite.shapes;

import java.awt.*;

public interface Shape {
    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void move(int x, int y);

    boolean isInsideBounds(int x, int y);

    void paint(Graphics graphics);
}
