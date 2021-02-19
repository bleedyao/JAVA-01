package com.bleedyao;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aop {
    // 前置方法
    public void startTransaction() {
        System.out.println("===> before transaction");
    }

    // 后置方法
    public void commitTransaction() {
        System.out.println("===> finish transaction");
    }

    // 环绕方法
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("===> around begin print");
        point.proceed();
        System.out.println("===> around finish print");
    }
}
