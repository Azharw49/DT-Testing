package com.example.demo.config;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.example.demo.annotations.LazyConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;


@LazyConfiguration
public class WebDriverConfig {

	Logger LOG = LoggerFactory.getLogger(WebDriverConfig.class);

	@Bean
	@ConditionalOnProperty(name = "browser" , havingValue = "firefox")
	public WebDriver firefoxDriver()
	{
		WebDriverManager.firefoxdriver().driverVersion("0.30.0").setup();
		return new FirefoxDriver();
	}

	@Bean
	@ConditionalOnMissingBean
	public WebDriver chromeDriver()
	{
		WebDriverManager.chromedriver().driverVersion("98.0.4758.102").setup();
		return new ChromeDriver();
	}





}
