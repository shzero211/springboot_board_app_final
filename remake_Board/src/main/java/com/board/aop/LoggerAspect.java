package com.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//핵심로직안에서 부가적인 비즈니스가 사용될때를 하나의 단위로 묶는 모듈화를 한것(AOP)
@Component
@Aspect
public class LoggerAspect {
private final Logger logger=LoggerFactory.getLogger(this.getClass());
@Around("execution(* com.board..controller.*Controller.*(..)) or execution(* com.board..service.*Impl.*(..)) or execution(* com.board..mapper.*Mapper.*(..))")
public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

	String type = "";
	String name = joinPoint.getSignature().getDeclaringTypeName();

	if (name.contains("Controller") == true) {
		type = "Controller ===> ";

	} else if (name.contains("Service") == true) {
		type = "ServiceImpl ===> ";

	} else if (name.contains("Mapper") == true) {
		type = "Mapper ===> ";
	}

	logger.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
	return joinPoint.proceed();
}
}
