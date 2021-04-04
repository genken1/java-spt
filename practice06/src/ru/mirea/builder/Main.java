package ru.mirea.builder;

import ru.mirea.builder.builders.CarBuilder;
import ru.mirea.builder.builders.CarManualBuilder;
import ru.mirea.builder.cars.Car;
import ru.mirea.builder.cars.Manual;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());


        CarManualBuilder manualBuilder = new CarManualBuilder();

        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }
}
