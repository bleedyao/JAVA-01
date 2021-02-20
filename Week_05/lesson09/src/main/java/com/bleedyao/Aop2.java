package com.bleedyao;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Aop2 {
    @Pointcut(value = "execution(* com.bleedyao.ISchool.*(..))")
    public void print() {

    }

    @Before(value = "print()")
    public void before() {
        System.out.println("    ===> aop2 before");
    }

    @AfterReturning(value = "print()")
    public void after() {
        System.out.println("    ===> aop2 after");
    }

    @Around(value = "print()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("    ===> begin aop2 around");
        point.proceed();
        System.out.println("    ===> finish aop2 around");
    }
}
