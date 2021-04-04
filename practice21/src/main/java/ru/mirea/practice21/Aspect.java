package ru.mirea.practice21;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Around("allServiceMethods()")
    public Object logAroundTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: {}MSec, method signature: {}", elapsedTime, joinPoint.getSignature());
        return res;
    }

    @Pointcut("within(ru.mirea.practice21.services.*)")
    public void allServiceMethods() {}
}
