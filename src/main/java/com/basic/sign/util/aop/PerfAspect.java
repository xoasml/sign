package com.basic.sign.util.aop;

import com.basic.sign.util.aop.annotation.MethodInfoLogging;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP 작성 클래스
 *
 * @since 1.0 2023-02-24
 */
@Slf4j
@Component
@Aspect
public class PerfAspect {

    /**
     * 메소드 작업 진행 경과 시간을 log에 출력해준다.
     * <pre>
     * {@code
     * Ex)
     * @MethodInfoLogging(메소드 설명) // 어노테이션 입력 하면 적용 됨
     * public Method(  ) {...}
     * }
     * </pre>
     *
     * @param pjp ProceedingJoinPoint
     * @return Object retVal = pjp.proceed();
     * @throws Throwable AOP 메소드에서 익셉션 발생 시 대비
     * @since v1.0 2023/02/24
     */
    @Around("@annotation(com.basic.sign.util.aop.annotation.MethodInfoLogging) && @annotation(target)")
    public Object workTime(ProceedingJoinPoint pjp, MethodInfoLogging target) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodPath = pjp.getTarget().getClass().getName();
        String className = methodPath.substring(methodPath.lastIndexOf(".") + 1);
        String methodName = signature.getMethod().getName();
        String classMethod = className + "." + methodName;

        log.info("START {}()", classMethod);
        log.info("Method Path : {}", methodPath);
        log.info("Description : {}", target.description());

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        Object retVal = pjp.proceed();

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

        log.info("Execute Time : {}ms", afterTime - beforeTime);
        log.info("END {}()\n", classMethod);

        return retVal;
    }

    @Around("execution( * com.miroit.TagApi.domain.*.*Controller.*(..))")
    public Object keepLogin(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object proceed = jp.proceed();
        /* 추후에 사용할 일이 있을것 같아 만들어 뒀습니다 */
        return proceed;
    }

}
