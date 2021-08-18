package com.cbs.edu.springbootsecurityjwt.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogExecutionTimeAspect {

    @Pointcut()
    public void logExecutionTimeJointPoint() {
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long end = System.currentTimeMillis();

        log.info("{} executed in {} ms", joinPoint.getSignature(), end - start);

        return proceed;
    }

    @AfterReturning(value = "execution(* com.cbs.edu.springbootsecurityjwt.controller.TicketController.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("Result: {}", result);
    }
}
