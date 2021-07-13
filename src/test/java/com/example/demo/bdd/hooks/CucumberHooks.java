package com.example.demo.bdd.hooks;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.demo.annotations.LazyAutowired;
import com.example.demo.service.ScreenshotService;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class CucumberHooks {
	
	Logger LOG = LoggerFactory.getLogger(CucumberHooks.class);

    @LazyAutowired
    private ScreenshotService screenshotService;
    
    @Autowired
    ApplicationContext ctx;

    @LazyAutowired
    private ApplicationContext applicationContext;

    @After(order=0)
    public void afterScenarioError(Scenario scenario){
        if(scenario.isFailed()){
        	LOG.error("Scenario : {} is failed",scenario.getName());
            scenario.attach(this.screenshotService.getScreenshot(), "image/png", scenario.getName());
            }else 
            {
            	LOG.info("Scenario : {} is passed",scenario.getName());
                scenario.attach(this.screenshotService.getScreenshot(), "image/png", scenario.getName());
            }
        }
        
          
        
    }
    
    



