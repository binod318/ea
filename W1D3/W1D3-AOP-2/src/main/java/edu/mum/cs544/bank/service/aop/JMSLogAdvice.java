package edu.mum.cs544.bank.service.aop;

import edu.mum.cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMSLogAdvice {
    @Autowired
    private ILogger logger;

    @After("execution(* edu.mum.cs544.bank.jms.JMSSender.*(..))")
    public void LogJMSAfter(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String message = (String)args[0];
        logger.log("JMS Sender log, method: " + joinPoint.getSignature().getName() + " message: " + message);
    }
}
