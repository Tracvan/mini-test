package com.codegym.minitest.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Aspect
public class Logger {
    @AfterThrowing(pointcut = "execution(public * com.codegym.minitest.service.TourService.*(..))", throwing = "e")
    public void logClass(JoinPoint joinPoint, DataIntegrityViolationException e) {
        System.out.printf("Trung code" + e.getMessage());
    }

}
