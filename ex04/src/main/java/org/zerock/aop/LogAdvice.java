package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // 해당 클래스의 객체가 Aspect를 구현한 것을 안내
@Log4j
@Component //  스프링에서 빈으로 인식하기 위해 사용
public class LogAdvice {

	
	// AspectJ의 표현식  맨 앞의 ‘*’는 접근제한자를 의미
	// 맨 뒤의 ‘*’는 클래스의 이름과 메서드의 이름을 의미
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		
		log.info("=================");
	}
	
	// execution시작 하는 Pointcut 설정에 doAdd() 메서드를 명시하고 파라미터의 타입을 지정한다.
	// 뒤쪽 && args(str1, str2)") 부분은 변수 수명을 지정한다
    @Before("execution(* org.zerock.service.SampleService*.doAdd(String, String))"
    		+ " && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
 
      log.info("str1: " + str1);
      log.info("str2: " + str2);
    }  
    
    
    // @AfterThrowing은 pointcut과 throwing을 속성으로 지정하고
    // 변수 이름을 exception이라 지정했다.
    @AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
    public void logException(Exception exception) {
      
      log.info("Exception....!!!!");
      log.info("exception: "+ exception);
    
    }
    
    //  @Around가 적용되는 메서드의 경우 리턴 타입이 void가 아닌 타입으로 설정
    // Pointcut 설정은 ‘…SampleService*.*(..)’로 지정
    // ProceedingJoinPoint는 AOP 대상이 되는 Target이나 파라미터 등을 파악할 뿐 아니라 직접 실행을 정한다
    @Around("execution(* org.zerock.service.SampleService*.*(..))")
    public Object logTime (ProceedingJoinPoint pjp) {
    	
    	// 실행 시작 시간 구하기
    	long start = System.currentTimeMillis();
    	
    	log.info("Target : " + pjp.getTarget());
    	log.info("Param : " + Arrays.toString(pjp.getArgs()));
    	
    	//invoke method 
        Object result = null;
        
        try {
          result = pjp.proceed();
        } catch (Throwable e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
        // 실행 끝 시간 구하기 
        long end = System.currentTimeMillis();
        
        log.info("TIME: "  + (end - start));
        
        return result;
    	
    	
    	
    }
}