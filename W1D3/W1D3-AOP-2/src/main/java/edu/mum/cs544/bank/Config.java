package edu.mum.cs544.bank;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("edu.mum.cs544.bank")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Config {
}
