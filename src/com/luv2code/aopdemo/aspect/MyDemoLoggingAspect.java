package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=========>>> Executing @Around on method: "+ method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e){

            System.out.println(e.getMessage());

            throw e;
        }


        long end = System.currentTimeMillis();

        long duration = end - begin;

        System.out.println("\n==========> Duration: " + duration/1000.0 + " seconds");

        return result;
    }


    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint){

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=========>>> Executing @After (Finally) on method: "+ method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc){

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=========>>> Executing @AfterThrowing on method: "+ method);

        System.out.println("\n=========>>> The Exception is "+ exc);


    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=========>>> Executing @AfterReturning on method: "+ method);

        System.out.println("\n=========>>> result is: "+ result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n=========>>> upperCased result is: "+ result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for(Account tempAccount : result){
            tempAccount.setName(tempAccount.getName().toUpperCase());
        }
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=======>>> Executing @Before advice on method");

        //display method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        //display method arguments
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args){
            System.out.println(tempArg);

            if (tempArg instanceof Account){
                Account account = (Account) tempArg;

                System.out.println("account name " + account.getName());
                System.out.println("account level " + account.getLevel());

            }
        }
    }

}
