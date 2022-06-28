package com.practice.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	
	private static Logger logger= LoggerFactory.getLogger(LoggerAspect.class);

	@Before("execution(* com.practice.*.* (..))")
	public void BeforeFetchAll() {
		logger.info("INSIDE EXECUTION EXPRESS MOVIE AOP");
	}
	@Before("execution(* com.practice.*.add (..))")
	public void add() {
		logger.info("INSERT EXECUTION EXPRESS MOVIE AOP");
	}
	
	@Before("execution(* com.practice.Service.MovieService.searchBasedOnTitle (..))")
	public void SearchImpl() {
		logger.info("---------SEARCH ----------");
	}
	@Before("execution(* com.practice.Service.MovieService.getDirectorListFromTitle (..))")
	public void directorSearch() {
		logger.info("---------DIRECTOR LIST FROM MOVIE ----------");
	}
	
	
}
