package ru.mirea.builder.builders;

import ru.mirea.builder.cars.CarType;
import ru.mirea.builder.components.Engine;
import ru.mirea.builder.components.GPSNavigator;
import ru.mirea.builder.components.Transmission;
import ru.mirea.builder.components.TripComputer;

public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
