package com.example.demo.service;

import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Lazy
@Service
public class ScreenshotService {
	
	private static final Logger LOG = Logger.getLogger(ScreenshotService.class.getName()); 
	
	@Autowired
	ApplicationContext ctx;
	
	
	
	public byte[] getScreenshot()
	{
		return this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
	}

}
