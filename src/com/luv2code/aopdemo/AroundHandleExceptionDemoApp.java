package com.luv2code.aopdemo;

import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AroundHandleExceptionDemoApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("MAIN APP AU AU : \n");

        System.out.println("Calling getFortune()");

        boolean tripWire = true;

        System.out.println(trafficFortuneService.getFortune(tripWire));

        System.out.println("Finished");


       context.close();
    }
}
