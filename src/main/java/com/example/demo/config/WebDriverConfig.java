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
		WebDriverManager.firefoxdriver().version("0.29.1").setup();
		WebDriver driver = new FirefoxDriver();
		//Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				LOG.info("opening reports...............");
				((JavascriptExecutor) driver).executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get(System.getProperty("user.dir") + "/target/cucumber-report-html/cucumber-html-reports/feature-overview.html");
			}});
		return driver;
	}

	@Bean
	@ConditionalOnMissingBean
	public WebDriver chromeDriver()
	{
		WebDriverManager.chromedriver().version("90.0.4430.24").setup();
		WebDriver driver = new ChromeDriver();
		//Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				LOG.info("opening reports...............");
				((JavascriptExecutor) driver).executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get(System.getProperty("user.dir") + "/target/cucumberreport.html");
				driver.navigate().refresh();
			}});
		return driver;
	}





}
