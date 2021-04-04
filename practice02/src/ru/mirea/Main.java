package ru.mirea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Human human1 = new Human(23, "Дмитрий", "Ланкин", LocalDate.of(1997, 4, 17), 70);
        Human human2 = new Human(24, "Артур", "Далалоян", LocalDate.of(1996, 4, 26), 63);
        Human human3 = new Human(23, "Никита", "Нагорный", LocalDate.of(1997, 2, 12), 66);
        Human human4 = new Human(18, "Степан", "Анохин", LocalDate.of(2002, 1, 7), 80);
        Human human5 = new Human(76, "Роберт", "Фишер", LocalDate.of(1943, 3, 9), 69);

        ArrayList<Human> list = new ArrayList<>();
        list.add(human1);
        list.add(human2);
        list.add(human3);
        list.add(human4);
        list.add(human5);

        // Сортировка по возрасту
        System.out.println("Сортировка по возрасту");
        list.stream().sorted(Comparator.comparingInt(Human::getAge))
                .forEach(System.out::println);
        // Фильтрация по возрасту меньше, чем 20
        System.out.println("\nФильтрация по возрасту меньше, чем 20");
        list.stream().filter(human -> human.getAge() < 20)
                .forEach(System.out::println);
        // Фильтрация по имени "содержит `е`"
        System.out.println("\nФильтрация по имени \"содержит `е`\"");
        list.stream().filter(human -> human.getFirstName().contains("е"))
                .forEach(System.out::println);
        // Конкатенация первых букв имен
        System.out.println("\nКонкатенация первых букв имен");
        String concat = list.stream().reduce("", (fl, hum)->
                (fl + hum.getFirstName().charAt(0)),
                (fl, sl) -> fl+sl);
        System.out.println(concat);
    }
}
