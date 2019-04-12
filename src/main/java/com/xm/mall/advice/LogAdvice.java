package com.xm.mall.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * log
 * 自定义通知
 *
 * @author huyan
 * @date 2019-04-03 17:10
 */
@Aspect
@Component
@Slf4j
public class LogAdvice {

    /**
     *  注解方式设置切点
     */
    @Pointcut("execution(* com.xm.mall.service.impl.*.*(..))")
    public void pointcutService(){}

    @Pointcut("execution(* com.xm.mall.controller.*.*(..))")
    public void pointcutController(){}

    /**
     * 环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("LogAdvice.pointcutController()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("ControllerStart");

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数："+arg);
        }

        String name = joinPoint.getSignature().getName();
        System.out.println("方法名："+name);

        Object proceed = joinPoint.proceed();

        System.out.println("ControllerEnd");
        //如果是AOP环绕或动态代理、静态代理、cglib代理,被代理对象有返回值或代理对象与被代理对象还有再次调用被代理对象的方法并使用其返回值时,被代理对象有返回值必须在代理对象中将执行方法后的值进行返回。
        return proceed;
    }

    //之前通知
    @Before("LogAdvice.pointcutService()")
    //JoinPoint 为AOP切面中操作唯一参数，可以操作被代理对象，获取被代理对象的信息等
    public void lt(JoinPoint point){

        System.out.println("ServiceStart");
        //获取目标方法名称
        String methodName = point.getSignature().getName();
        //获取目标的类型
        String clazz = point.getSignature().getDeclaringTypeName();
        //获取目标方法的参数
        String args = Arrays.toString(point.getArgs());
        //打印输出
        System.out.println("方法为："+clazz+","+methodName+",参数为："+args);

        System.out.println("ServiceEnd");

    }

    @Around("LogAdvice.pointcutService()")
    public Object logs(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().getName();
        log.info(methodName+"开始");

        Object proceed = point.proceed();
        log.info(methodName+"方法结束");
        return proceed;
    }


}
