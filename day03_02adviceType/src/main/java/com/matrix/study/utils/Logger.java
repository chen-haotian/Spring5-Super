package com.matrix.study.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: Matrix
 * @CreateDate: 2022-05-07 12:50:20
 * @Description: TODO 用于记录日志的工具类
 * @Version: TODO
 */
public class Logger {

    /**
     * 前置通知
     */
    public void beforePrintLog() {
        System.out.println("前置通知Logger类的beforePrintLog方法开始记录日志!");
    }

    /**
     * 后置通知
     */
    public void afterReturningPrintLog() {
        System.out.println("后置通知Logger类的afterReturningPrintLog方法开始记录日志!");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知Logger类的afterThrowingPrintLog方法开始记录日志!");
    }

    /**
     * 最终通知
     */
    public void afterPrintLog() {
        System.out.println("最终通知Logger类的afterPrintLog方法开始记录日志!");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *      通过对比动态代理的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口，ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，Spring框架会为我们提供该接口的实现类供我们使用。
     * Spring中的环绕通知：
     *      它是Spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint) {
        Object rtValue = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs(); // 得到方法执行时需要的参数

            System.out.println("环绕通知Logger类的aroundPrintLog方法开始记录日志---->前置通知");

            rtValue = proceedingJoinPoint.proceed(args); // 明确调用业务层方法（切入点的方法）

            System.out.println("环绕通知Logger类的aroundPrintLog方法开始记录日志---->后置通知");

            return rtValue;
        } catch (Throwable e) {
            System.out.println("环绕通知Logger类的aroundPrintLog方法开始记录日志---->异常通知");
            throw new RuntimeException(e);
        } finally {
            System.out.println("环绕通知Logger类的aroundPrintLog方法开始记录日志---->最终通知");
        }
    }
}
