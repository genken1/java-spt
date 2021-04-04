package ru.mirea.practice13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Student {
    @Value("${student.name}")
    private String userName;
    @Value("${student.last_name}")
    private String userLastName;
    @Value("${student.group}")
    private String userGroup;
    private final Logger logger = LoggerFactory.getLogger(Student.class);

    @PostConstruct
    private void init() {
        logger.info("Student {} {} from {}", userName, userLastName, userGroup);
    }
}
