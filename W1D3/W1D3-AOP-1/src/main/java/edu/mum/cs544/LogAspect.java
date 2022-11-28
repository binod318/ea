package edu.mum.cs544;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

//    @After("execution(* edu.mum.cs544.CustomerService.*(..))")
    @After("execution(* edu.mum.cs544.EmailSender.*(..))")
    public void logAfter(JoinPoint joinPoint){
        SimpleDateFormat formatter= new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        Date date = new Date(System.currentTimeMillis());

        Object[] args = joinPoint.getArgs();
        String email = (String)args[0];
        String message = (String)args[1];
        EmailSender sender = (EmailSender)joinPoint.getTarget();
        String server = sender.getOutgoingMailServer();

        System.out.println(formatter.format(date)+ " Method= " + joinPoint.getSignature().getName() +
                " address=" + email + " message=" + message + " Outgoing mail server=" + server);
    }

    /*
    Write a new advice that calculates the duration of the method calls to the DAO object and outputs the result to the console.
    Spring provides a stopwatch utility that can be used for this by using the following code:
    */
    @Around("execution(* edu.mum.cs544.CustomerDAO.save(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("Total time " + totalTime);
        return retVal;
    }
}
