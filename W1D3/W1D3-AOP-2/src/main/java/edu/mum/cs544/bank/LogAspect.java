package edu.mum.cs544.bank;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//@Aspect
//@Component
public class LogAspect {
    @Autowired
    private ILogger logger;

    @Before("execution(* edu.mum.cs544.bank.dao.*.*(..))")
    public void LogBefore(JoinPoint joinPoint){
        //System.out.println("Before execution of: " + joinPoint.getSignature().getName());
        logger.log("Before execution of: " + joinPoint.getSignature().getName());
    }

    @After("execution(* edu.mum.cs544.bank.dao.*.*(..))")
    public void LogAfter(JoinPoint joinPoint){
        //System.out.println("After execution of: " + joinPoint.getSignature().getName());
        logger.log("After execution of: " + joinPoint.getSignature().getName());
    }

    @After("execution(* edu.mum.cs544.bank.jms.JMSSender.*(..))")
    public void LogJMSAfter(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String message = (String)args[0];
        logger.log("JMS Sender log, method: " + joinPoint.getSignature().getName() + " message: " + message);
    }

    @Around("execution(* edu.mum.cs544.bank.service.*.*(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable{
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("Total time for method " + call.getSignature().getName() + " is " + totalTime);
        return retVal;
    }
}
