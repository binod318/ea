package edu.mum.cs544.bank.service.aop;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DAOLogAdvice {
    @Autowired
    private ILogger logger;

    @Before("execution(* edu.mum.cs544.bank.dao.*.*(..))")
    public void DAOLogBefore(JoinPoint joinPoint){
        //System.out.println("Before execution of: " + joinPoint.getSignature().getName());
        logger.log("Before execution of: " + joinPoint.getSignature().getName());
    }

    @After("execution(* edu.mum.cs544.bank.dao.*.*(..))")
    public void DAOLogAfter(JoinPoint joinPoint){
        //System.out.println("After execution of: " + joinPoint.getSignature().getName());
        logger.log("After execution of: " + joinPoint.getSignature().getName());
    }
}
