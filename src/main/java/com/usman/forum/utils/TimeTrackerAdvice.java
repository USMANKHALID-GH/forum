package com.usman.forum.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class TimeTrackerAdvice {

    @Around("@annotation(com.usman.forum.Annotations.TrackTime)")
    public  Object trackTime(ProceedingJoinPoint asp) throws Throwable {
        Long startTime=System.currentTimeMillis();

        Object obj=asp.proceed();
        Long endTime=System.currentTimeMillis();

        log.info("\nMETHOD NAME: "+asp.getSignature() +" \nthe trackTime is : "+ (endTime-startTime));

        return obj;
    }
}
