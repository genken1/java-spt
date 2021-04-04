package ru.mirea.practice10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.practice10.Programmers.Junior;
import ru.mirea.practice10.Programmers.Middle;
import ru.mirea.practice10.Programmers.Senior;

@Configuration
public class BeanConfig {
    @Bean
    public Middle middle() {
        return new Middle();
    }

    @Bean
    public Senior senior() {
        return new Senior();
    }

    @Bean
    public Junior junior() {
        return new Junior();
    }
}
