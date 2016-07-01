package com.netease.course.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: atwjsw
 * @Description: logging ascpect class. use log4j to log all method operation of
 *               web controller, and log any exception.
 * @Date: Apr 20, 2016 3:39:54 PM
 */
@Component
@Aspect
public class LoggingAspect {

	private Logger logger = Logger.getLogger(LoggingAspect.class);

	@AfterReturning("execution(* com.netease.course.web.controller.*.*(..))")
	private void normalOpDoLog(JoinPoint jp) {
		logger.info("********************************************");
		logger.info("Controller executed: " + jp.getSignature());
		Object[] args = jp.getArgs();
		logger.info(args.length + " parameters: ");
		for (int i = 0; i < args.length; i++) {
			logger.info(args[i].getClass() + ": " + args[i].toString() + " ");
			// System.out.print(args[i] + " ");
		}
		logger.info("*********************************************");
	}

	@AfterThrowing(pointcut = "execution(* com.netease.course.web.controller.*.*(..))", throwing = "ex")
	private void exceptionDoLog(JoinPoint jp, Exception ex) {
		logger.error("----------------------------------------------");
		logger.error("Method executed: " + jp.getSignature());
		logger.error("Exception threw: " + ex.getMessage());
		Object[] args = jp.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.error(args[i].getClass() + ": " + args[i].toString());
		}
	}

}