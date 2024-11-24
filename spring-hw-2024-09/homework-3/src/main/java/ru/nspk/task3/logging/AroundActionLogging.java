package ru.nspk.task3.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AroundActionLogging {

    @Around("@annotation(ru.nspk.task3.logging.Loggable)")
    public Object action(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("proxy class:{}", joinPoint.getThis().getClass());
        log.info("invoking method:{}, args:{}", joinPoint.getSignature(), joinPoint.getArgs());

        var result = joinPoint.proceed();

        log.info("after invoking method:{}, result:{}", joinPoint.getSignature(), result);
        return result;
    }
}
