package com.demo.JobListing.Logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private static Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);

    @Before("execution(public * com.demo.JobListing.controllers.PostController.getPosts())")
    public void log(){
        LOGGER.info("Logging actions...");
    }

}
