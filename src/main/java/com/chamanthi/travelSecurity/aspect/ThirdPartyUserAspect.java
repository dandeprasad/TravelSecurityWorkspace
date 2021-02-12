package com.chamanthi.travelSecurity.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ThirdPartyUserAspect
{
    @Before(value = "execution(* com.chamanthi.travelSecurity.service.ThirdPartyUserService.*(..))")
    public void beforeAdvice(JoinPoint joinPoint/*, String empId, String fname, String sname*/) {
        System.out.println("Before method:" + joinPoint.getSignature());
        //System.out.println("Creating Employee with first name - " + fname + ", second name - " + sname + " and id - " + empId);
    }
}