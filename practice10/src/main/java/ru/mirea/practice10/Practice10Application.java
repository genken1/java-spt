package ru.mirea.practice10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mirea.practice10.Programmers.Junior;
import ru.mirea.practice10.Programmers.Middle;
import ru.mirea.practice10.Programmers.Senior;

@SpringBootApplication
public class Practice10Application {

    public static void main(String[] args) {
        SpringApplication.run(Practice10Application.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Middle middle = context.getBean(Middle.class);
        Senior senior = context.getBean(Senior.class);
        Junior junior = context.getBean(Junior.class);

        middle.doCoding();
        senior.doCoding();
        junior.doCoding();
    }

}
