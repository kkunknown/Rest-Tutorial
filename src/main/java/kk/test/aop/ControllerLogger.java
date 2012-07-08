package kk.test.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ControllerLogger {

	Logger log = Logger.getLogger(ControllerLogger.class);
	
	
//	@Pointcut("execution(* kk.test.controller.*.*(..))")
	@Pointcut("within(kk.test.controller..*)")
	public void businessMethods(){}
	
	@Around("businessMethods()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable{
		
		
		log.debug("aop logging start.");
		Object output = (String) pjp.proceed();
		log.debug("kind: " + pjp.getKind());
		log.debug("Signature Name: " + pjp.getSignature().getName());
		
		log.debug("aop logging end.");
		return output;
		
		
	}
	
}
